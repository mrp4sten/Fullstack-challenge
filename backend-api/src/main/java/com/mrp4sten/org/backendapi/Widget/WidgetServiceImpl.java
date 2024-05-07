package com.mrp4sten.org.backendapi.Widget;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class WidgetServiceImpl implements WidgetService {
  private WidgetRepository widgetRepository;

  public WidgetServiceImpl(WidgetRepository widgetRepository) {
    this.widgetRepository = widgetRepository;
  }

  @Override
  public Widget createWidget(Widget widget) {
    Optional<Widget> widgetOptional = widgetRepository.findByName(widget.getName());
    if (widgetOptional.isPresent()) {
      throw new IllegalArgumentException("Widget with name " + widget.getName() + " already exists");
    }

    return widgetRepository.save(widget);
  }

  @Override
  public List<Widget> getWidgets() {
    return widgetRepository.findAll();
  }

  @Override
  public Optional<Widget> getWidget(String name) {
    return widgetRepository.findByName(name);
  }

  @Override
  public Widget updateWidget(String name, Widget updatedWidget) {
    return widgetRepository.findByName(name)
        .map(widget -> {
          widget.setName(updatedWidget.getName());
          widget.setDescription(updatedWidget.getDescription());
          widget.setPrice(updatedWidget.getPrice());
          return widgetRepository.save(widget);
        })
        .orElseThrow(() -> new IllegalArgumentException("Widget not found with name " + name));
  }

  @Override
  public void deleteWidget(String name) {
    Widget widget = widgetRepository.findByName(name)
        .orElseThrow(() -> new IllegalArgumentException("Widget with name " + name + " not found"));
    widgetRepository.delete(widget);
  }
}
