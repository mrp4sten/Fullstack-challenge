package com.mrp4sten.org.backendapi.Widget;

import java.util.List;
import java.util.Optional;

public interface WidgetService {
  Widget createWidget(Widget widget);
  List<Widget> getWidgets();
  Optional<Widget> getWidget(String name);
  Widget updateWidget(String name, Widget widget);
  void deleteWidget(String name);
}
