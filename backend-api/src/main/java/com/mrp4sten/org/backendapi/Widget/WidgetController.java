package com.mrp4sten.org.backendapi.Widget;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/widgets")
public class WidgetController {
  private WidgetService widgetService;

  public WidgetController(WidgetService widgetService) {
    this.widgetService = widgetService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Widget createWidget(@RequestBody Widget widget) {
    return widgetService.createWidget(widget);
  }

  @GetMapping
  public List<Widget> getWidgets() {
    return widgetService.getWidgets();
  }

  @GetMapping("/{name}")
  public ResponseEntity<Widget> getWidget(@PathVariable("name") String name) {
    return widgetService.getWidget(name)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/{name}")
  public ResponseEntity<Widget> updateWidget(@PathVariable String name, @RequestBody Widget updatedWidget) {
    try {
      Widget widget = widgetService.updateWidget(name, updatedWidget);
      return new ResponseEntity<>(widget, HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{name}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteWidget(@PathVariable String name) {
    widgetService.deleteWidget(name);
  }
}
