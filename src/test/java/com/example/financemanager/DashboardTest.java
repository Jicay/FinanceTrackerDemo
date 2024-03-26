package com.example.financemanager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.MenuItemMatchers;
import org.testfx.robot.Motion;
import org.testfx.util.NodeQueryUtils;
import org.testfx.util.WaitForAsyncUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

@ExtendWith(ApplicationExtension.class)
public class DashboardTest {

    @Start
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(FinanceTrackerApplication.class.getResource("dashboard-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @BeforeEach
    public void setUp(FxRobot robot) throws TimeoutException {
        robot.clickOn("Navigation");

        WaitForAsyncUtils.waitFor(2, TimeUnit.SECONDS, () -> robot.lookup("Tableau de bord").match(NodeQueryUtils.isVisible()).tryQuery().isPresent());

        robot.clickOn("Tableau de bord", Motion.VERTICAL_FIRST, MouseButton.PRIMARY);
    }

    @Test
    public void shouldHaveMenu(FxRobot robot) {
        verifyThat(".menu-bar", isVisible());
        robot.lookup(".menu-bar").queryAs(MenuBar.class).getMenus().forEach(menu -> {
            verifyThat(menu, MenuItemMatchers.hasText("Navigation"));
            verifyThat(menu.getItems().get(0), MenuItemMatchers.hasText("Tableau de bord"));
            verifyThat(menu.getItems().get(1), MenuItemMatchers.hasText("Dépenses"));
        });
    }

    @Test
    public void shouldChangeStageWhenClickOnMenu(FxRobot robot) throws TimeoutException {
        robot.clickOn("Navigation");

        WaitForAsyncUtils.waitFor(2, TimeUnit.SECONDS, () -> robot.lookup("Dépenses").match(NodeQueryUtils.isVisible()).tryQuery().isPresent());

        robot.clickOn("Dépenses", Motion.VERTICAL_FIRST, MouseButton.PRIMARY);

        verifyThat(".title-text", hasText("Tableau récapitulatif des dépenses"));
    }

    @Test
    public void shouldHaveTitle() {
        verifyThat(".title-text", hasText("Tableau de bord"));
    }

    @Test
    public void shouldHaveChoiceBoxWithDate(FxRobot robot) {
        verifyThat("#periodChoiceBox", isVisible());

        LocalDate date = LocalDate.now();

        List<String> periodItems = robot.lookup("#periodChoiceBox").queryAs(ChoiceBox.class).getItems();

        for (int i = 0; i < 12; i++) {
            assertThat(periodItems, hasItem(date.format(DateTimeFormatter.ofPattern("MMMM yyyy"))));
            date = date.minusMonths(1);
        }
        assertThat(periodItems, hasSize(12));
    }

    @Test
    public void shouldHaveCharts(FxRobot robot) {
        verifyThat("#pieChart", isVisible());

        assertThat(robot.lookup("#pieChart").queryAs(PieChart.class).getTitle(), equalTo("Répartition des dépenses"));

        verifyThat("#lineChart", isVisible());

        assertThat(robot.lookup("#lineChart").queryAs(LineChart.class).getTitle(), equalTo("Évolution des dépenses"));
    }
}
