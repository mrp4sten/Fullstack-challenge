package com.mrp4sten.org.backendapi.Widget;

import org.springframework.stereotype.Service;

@Service
public class WidgetService {

  private final WidgetRepository widgetRepository;

  public WidgetService(WidgetRepository widgetRepository) {
    this.widgetRepository = widgetRepository;
  }

  public WidgetDTO createWidget(WidgetDTO widgetDTO) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'createWidget'");
  }

  public void deleteWidget(String name) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteWidget'");
  }

  public WidgetDTO getWidget(String name) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getWidget'");
  }

  public Iterable<WidgetDTO> getWidgets() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getWidgets'");
  }

  public WidgetDTO updateWidget(WidgetDTO widgetDTO) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateWidget'");
  }

  public void deleteAllWidgets() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteAllWidgets'");
  }
}
