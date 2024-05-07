package com.mrp4sten.org.backendapi;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrp4sten.org.backendapi.Widget.Widget;
import com.mrp4sten.org.backendapi.Widget.WidgetService;

@WebMvcTest
public class WidgetControllerTests {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private WidgetService widgetService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void givenWidget_whenCreateWidget_thenReturnSavedWidget() throws Exception {
    // Given precondition/setup
    Widget widget = Widget.builder()
        .name("CREATE Widget")
        .description("Description of CREATE Widget")
        .price(new BigDecimal(0.99))
        .build();
    given(widgetService.createWidget(any(Widget.class)))
        .willAnswer(invocation -> invocation.getArgument(0));

    // When POST request is sent to /api/widgets
    ResultActions response = mockMvc.perform(post("/api/widgets")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(widget)));

    // Then the response status is 201 (created)
    // And the returned widget is as expected
    response.andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name").value(widget.getName()))
        .andExpect(jsonPath("$.description").value(widget.getDescription()))
        .andExpect(jsonPath("$.price").value(widget.getPrice()));
  }

  @Test
  public void givenListOfWidgets_whenGetWidgets_thenReturnWidgetList() throws Exception {
    // Given precondition/setup
    List<Widget> widgets = new ArrayList<>();
    widgets.add(Widget.builder()
        .name("Widget 1")
        .description("Description of Widget 1")
        .price(new BigDecimal(1.99))
        .build());
    widgets.add(Widget.builder()
        .name("Widget 2")
        .description("Description of Widget 2")
        .price(new BigDecimal(2.99))
        .build());
    given(widgetService.getWidgets()).willReturn(widgets);

    // When GET request is sent to /api/widgets
    ResultActions response = mockMvc.perform(get("/api/widgets"));

    // Then the response status is 200 (ok)
    // And the size of the response is equal to the size of the widgets list
    response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.size()").value(widgets.size()));
  }

  @Test
  public void givenWidgetName_whenGetWidgetByName_thenReturnWidget() throws Exception {
    // Given precondition/setup
    Widget widget = Widget.builder()
        .name("GET Widget")
        .description("Description of GET Widget")
        .price(new BigDecimal(0.99))
        .build();

    given(widgetService.getWidget(widget.getName())).willReturn(Optional.of(widget));

    // When GET request is sent to /api/widgets/{name}
    ResultActions response = mockMvc.perform(get("/api/widgets/{name}", widget.getName()));

    // Then the response status is 200 (ok)
    // And the returned widget is as expected
    response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.name").value(widget.getName()))
        .andExpect(jsonPath("$.description").value(widget.getDescription()))
        .andExpect(jsonPath("$.price").value(widget.getPrice()));

  }

  @Test
  public void givenBadWidgetName_whenGetWidgetByName_thenReturnEmpty() throws Exception {
    // Given precondition/setup
    Widget widget = Widget.builder()
        .name("GET Widget")
        .description("Description of GET Widget")
        .price(new BigDecimal(0.99))
        .build();

    given(widgetService.getWidget(widget.getName())).willReturn(Optional.empty());

    // When GET request is sent to /api/widgets/{name}
    ResultActions response = mockMvc.perform(get("/api/widgets/{name}", widget.getName()));

    // Then the response status is 404 (not found)
    response.andExpect(status().isNotFound())
        .andDo(print());
  }

  @Test
  public void givenWidgetName_whenPutWidget_thenReturnUpdatedWidget() throws Exception {
    // Given precondition/setup
    Widget widget = Widget.builder()
        .name("PUT Widget")
        .description("Description of PUT Widget")
        .price(new BigDecimal(0.99))
        .build();

    Widget updatedWidget = Widget.builder()
        .name("PUT Widget")
        .description("Updated description of PUT Widget")
        .price(new BigDecimal(1.99))
        .build();

    given(widgetService.updateWidget(widget.getName(), updatedWidget)).willReturn(updatedWidget);

    // When PUT request is sent to /api/widgets/{name}
    ResultActions response = mockMvc.perform(put("/api/widgets/{name}", widget.getName())
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(updatedWidget)));

    // Then the response status is 200 (ok)
    // And the returned widget is as expected
    response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.name").value(updatedWidget.getName()))
        .andExpect(jsonPath("$.description").value(updatedWidget.getDescription()))
        .andExpect(jsonPath("$.price").value(updatedWidget.getPrice()));
  }

  @Test
  public void givenWidgetName_whenDeleteWidget_thenStatus204() throws Exception {
    // Given precondition/setup
    String widgetName = "DELETE Widget";

    doNothing().when(widgetService).deleteWidget(widgetName);

    // When DELETE request is sent to /api/widgets/{name}
    ResultActions response = mockMvc.perform(delete("/api/widgets/{name}", widgetName)
        .contentType(MediaType.APPLICATION_JSON));

    // Then the response status is 204 (no content)
    response.andExpect(status().isNoContent())
        .andDo(print());
  }
}
