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

  /**
   * Create widget
   *
   * @param widget
   * @return Widget
   */
  @Override
  public Widget createWidget(Widget widget) {
    Optional<Widget> widgetOptional = widgetRepository.findByName(widget.getName());
    if (widgetOptional.isPresent()) {
      throw new IllegalArgumentException("Widget with name " + widget.getName() + " already exists");
    }

    return widgetRepository.save(widget);
  }

  /**
   * Get all widgets
   *
   * @return List of widgets
   */
  @Override
  public List<Widget> getWidgets() {
    return widgetRepository.findAll();
  }

  /**
   * Get widget by name
   *
   * @param name
   * @return Widget
   */
  @Override
  public Optional<Widget> getWidget(String name) {
    return widgetRepository.findByName(name);
  }

  /**
   * Update widget by name
   *
   * @param name
   * @param updatedWidget
   * @return Widget
   */
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


  /**
   * Delete widget by name
   *
   * @param name
   * @return void
   */
  @Override
  public void deleteWidget(String name) {
    Widget widget = widgetRepository.findByName(name)
        .orElseThrow(() -> new IllegalArgumentException("Widget with name " + name + " not found"));
    widgetRepository.delete(widget);
  }
}
