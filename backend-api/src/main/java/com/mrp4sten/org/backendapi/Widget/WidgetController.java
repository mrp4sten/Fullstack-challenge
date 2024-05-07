package com.mrp4sten.org.backendapi.Widget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/widget")
public class WidgetController {
  private final WidgetService widgetService;

  @Autowired
  public WidgetController(WidgetService widgetService) {
    this.widgetService = widgetService;
  }

  @PostMapping
  public WidgetDTO createWidget(WidgetDTO widgetDTO) {
    return widgetService.createWidget(widgetDTO);
  }

  @GetMapping
  public Iterable<WidgetDTO> getWidgets() {
    return widgetService.getWidgets();
  }

  @GetMapping
  public WidgetDTO getWidget(String name) {
    return widgetService.getWidget(name);
  }

  @PutMapping
  public WidgetDTO updateWidget(WidgetDTO widgetDTO) {
    return widgetService.updateWidget(widgetDTO);
  }

  @DeleteMapping
  public void deleteWidget(String name) {
    widgetService.deleteWidget(name);
  }

}
