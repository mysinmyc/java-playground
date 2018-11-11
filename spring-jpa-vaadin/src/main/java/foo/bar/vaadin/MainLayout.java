package foo.bar.vaadin;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLayout;


public class MainLayout extends Div implements RouterLayout {

    Div divContent = new Div();
    public MainLayout() {

        add(new Label("My Contact list"));
        HorizontalLayout buttonBar = new HorizontalLayout();
        add(buttonBar);
        buttonBar.add(new Button("List", e-> {
            getUI().get().navigate(ContactList.class);
        }),new Button("Add", e-> {
            getUI().get().navigate(ContactEditor.class);
        }));

        add(divContent);

    }

    @Override
    public void showRouterLayoutContent(HasElement content) {

        if (content==null) {
            return;
        }

        divContent.removeAll();
        divContent.add((Component) content);
    }
}
