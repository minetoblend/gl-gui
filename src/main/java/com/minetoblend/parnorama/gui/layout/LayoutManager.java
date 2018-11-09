package com.minetoblend.parnorama.gui.layout;

import com.minetoblend.parnorama.gui.element.Container;
import com.minetoblend.parnorama.gui.element.Element;

public interface LayoutManager {

    void layoutContainer(Container parent);

    void addLayoutComponent(Object param, Element element);

    void removeLayoutComponent(Object param, Element element);

}
