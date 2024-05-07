package com.mrp4sten.org.backendapi.Widget;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WidgetRepository extends JpaRepository<Widget, Long> {
  /**
   * Find widget by name
   * @param name
   * @return
   */
  Optional<Widget> findByName(String name);
}
