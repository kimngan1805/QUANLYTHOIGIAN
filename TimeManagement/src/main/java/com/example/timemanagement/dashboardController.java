package com.example.timemanagement;
import com.example.timemanagement.CONTROLLER.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;

public class dashboardController implements Initializable {
    @FXML
    private Label username;
    @FXML
    private CheckBox check1;

    @FXML
    private CheckBox check2;

    @FXML
    private CheckBox check3;

    @FXML
    private CheckBox check4;

    @FXML
    private CheckBox check5;

    @FXML
    private TextField list1;

    @FXML
    private TextField list2;

    @FXML
    private TextField list3;

    @FXML
    private TextField list4;

    @FXML
    private TextField list5;
    @FXML
    private TextField add_note;

    @FXML
    private Button add_note_btn;

    @FXML
    private Button add_btn_event;
    @FXML
    private Button update_btn_event;
    @FXML
    private Button delete_btn_event;
    @FXML
    private Button clear_btn_event;

    @FXML
    private TableColumn<TaskData, String> status_coll;

    @FXML
    private Button add_task;

    @FXML
    private Button clear_task;
    @FXML
    private DatePicker date_task;
    @FXML
    private Button delete_task;    @FXML
    private ComboBox<String> status_task;

    @FXML
    private TableView<TaskData> table_view_task;

    @FXML
    private TextArea task_area;




    @FXML
    private TableColumn<EventData, String> status_Column;

    @FXML
    private ComboBox<String> status_combo;


    @FXML
    private TableView<EventData> event_table_view;

    @FXML
    private TextArea event_textarea;


    @FXML
    private DatePicker event_date_end;

    @FXML
    private DatePicker event_date_starr;

    @FXML
    private TableColumn<EventData, String> date_End_Column;

    @FXML
    private TableColumn<EventData, String> date_start_Column;


    @FXML
    private TableColumn<EventData, String> event_Column;

    @FXML
    private TableColumn<TaskData, String> list_col_table;
    @FXML
    private TableColumn<TaskData, String> status_table;

    //////////////////////////////
    @FXML
    private TableColumn<Task, String> to_do_task_table;
    @FXML
    private Button update_task;

    @FXML
    private TableColumn<String,TaskData> todolisttask;
    @FXML
    private TableColumn<String, TaskData> datetask;

    @FXML
    private Button cancelButton;
    @FXML
    private Button event_btn;

    @FXML
    private AnchorPane event_form;

    @FXML
    private Button home_btn;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Button note_btn;

    @FXML
    private AnchorPane note_form;
    @FXML
    private TableView<TaskData> table_today;

    @FXML
    private PieChart pieChart;

    @FXML
    private Label quote;

    @FXML
    private Button task_btn;

    @FXML
    private AnchorPane task_form;
    private Connection connection;
    private PreparedStatement prepare;
    private ResultSet resultSet;
    private Statement statement;
    private Alert alert;
    @FXML
    private Button add_btn;
    @FXML
    private TextArea content_note;

    @FXML
    private Button update_btn;
    @FXML
    private TextField titile_note;
    private AlertMessage  alert2= new AlertMessage();

    public void switchform(ActionEvent event) {
        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            event_form.setVisible(false);
            task_form.setVisible(false);

            home_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46dddd,#fd427d);");
            event_btn.setStyle("-fx-background-color: transparent;");
            task_btn.setStyle("-fx-background-color: transparent;");
        } else if (event.getSource() == event_btn) {
            home_form.setVisible(false);
            event_form.setVisible(true);
            task_form.setVisible(false);

            event_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46dddd,#fd427d);");
            home_btn.setStyle("-fx-background-color: transparent;");
            task_btn.setStyle("-fx-background-color: transparent;");
        } else if (event.getSource() == task_btn) {
            home_form.setVisible(false);
            event_form.setVisible(false);
            task_form.setVisible(true);

            task_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46dddd,#fd427d);");
            event_btn.setStyle("-fx-background-color: transparent;");
            home_btn.setStyle("-fx-background-color: transparent;");
        } else if (event.getSource() == note_btn) {
            home_form.setVisible(false);
            event_form.setVisible(false);
            task_form.setVisible(false);

            note_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46dddd,#fd427d);");
            event_btn.setStyle("-fx-background-color: transparent;");
            task_btn.setStyle("-fx-background-color: transparent;");
            home_btn.setStyle("-fx-background-color: transparent;");
        }
    }


    public void ExitButton(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();

    }
    public ObservableList<EventData> eventDataList() {
        ObservableList<EventData> listData = FXCollections.observableArrayList();
        String selectData = "select * from Event";
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connect = connectNow.getConnection();
        try {
            prepare = connect.prepareStatement(selectData);
            resultSet=prepare.executeQuery();
            EventData eventData;
            while (resultSet.next()){
                eventData= new EventData(resultSet.getString("event"),resultSet.getDate("date_start"),
                        resultSet.getDate("date_end"),resultSet.getString("status"));
                listData.add(eventData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    private ObservableList<EventData>eventListData;
    public void showEventData(){
        eventListData=eventDataList();
        event_Column.setCellValueFactory(new PropertyValueFactory<>("Event"));
        status_Column.setCellValueFactory(new PropertyValueFactory<>("Date_start"));
        date_start_Column.setCellValueFactory(new PropertyValueFactory<>("date_end"));
        date_End_Column.setCellValueFactory(new PropertyValueFactory<>("Status"));
        event_table_view.setItems(eventListData);
    }
    private int eventID;
    public void eventSelectedData(){
        EventData eData=event_table_view.getSelectionModel().getSelectedItem();
        int num=event_table_view.getSelectionModel().getSelectedIndex();
        if((num-1)<-1) return;
        eventID=eData.getId();
        event_textarea.setText(eData.getEvent());
        event_date_starr.setValue(LocalDate.parse(String.valueOf(eData.getDate_start())));
        event_date_end.setValue(LocalDate.parse(String.valueOf(eData.getDate_end())));
        status_combo.getSelectionModel().select(eData.getStatus());
     }

    public void addEvent() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connect = connectNow.getConnection();
        try {
            if (event_textarea.getText().isEmpty() || event_date_starr.getValue() == null || event_date_end.getValue() == null || status_combo.getSelectionModel().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng điền vào đủ ô");
                alert.showAndWait();
            }else{
                if(event_date_starr.getValue().isAfter(event_date_end.getValue())){
                    alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Ngày không hợp lệ");
                    alert.showAndWait();
                }else{
                    String check="select event from event where event='"+event_textarea.getText()+"'";
                    prepare=connect.prepareStatement(check);
                    resultSet=prepare.executeQuery();
                    if(resultSet.next()){
                        alert=new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Sự kiện "+event_textarea.getText()+" đã có sẵn");
                        alert.showAndWait();
                    }else{
                        String insert="insert into Event(event,date_start,date_end,date,status)"+"values(?,?,?,?,?)";
                        prepare=connect.prepareStatement(insert);
                        prepare.setString(1,event_textarea.getText());
                        prepare.setString(2,String.valueOf(event_date_starr.getValue()));
                        prepare.setString(3,String.valueOf(event_date_end.getValue()));
                        Date date= new Date();
                        java.sql.Date sqlDate= new java.sql.Date(date.getTime());
                        prepare.setString(4,String.valueOf(sqlDate));
                        prepare.setString(5, (String) status_combo.getSelectionModel().getSelectedItem());
                        prepare.executeUpdate();
                        showEventData();
                        eventClear();
                    }

                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void eventUpdate(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connect = connectNow.getConnection();
        if(event_textarea.getText().isEmpty()||
                event_date_starr.getValue()==null||event_date_end.getValue()==null||
                status_combo.getSelectionModel().getSelectedItem().isEmpty()
        ) {
            alert2.ErrorMessage("Vui lòng chọn một sự kiện để cập nhật!");
        }else{
            if(alert2.ConfirmMessage("Bạn muốn cập nhật sự kiện này" +event_textarea.getText()));
            Date date= new Date();
            java.sql.Date sqlDate= new java.sql.Date(date.getTime());
            try{
                String update = "UPDATE Event SET event=?, date_start=?, date_end=?, status=? WHERE id=?";
                prepare = connect.prepareStatement(update);
                prepare.setString(1, event_textarea.getText());
                prepare.setString(2, String.valueOf(event_date_starr.getValue()));
                prepare.setString(3, String.valueOf(event_date_end.getValue()));
                prepare.setString(4, status_combo.getSelectionModel().getSelectedItem());
                prepare.setInt(5, eventID);
                prepare.executeUpdate();
                showEventData();
                alert2.SuccessMessage("Cập nhật thành công!");
                eventClear();
                connect.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void eventDelete(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connect = connectNow.getConnection();
        String currentID;
        if(event_textarea.getText().isEmpty()||
                event_date_starr.getValue()==null||event_date_end.getValue()==null||
                status_combo.getSelectionModel().getSelectedItem().isEmpty()
        ){
            alert2.ErrorMessage("Vui lòng điền đầy đủ vào các ô!");
        }else{
            if(alert2.ConfirmMessage("Bạn muốn xoá sự kiện này" +event_textarea.getText()));
            Date date= new Date();
            java.sql.Date sqlDate= new java.sql.Date(date.getTime());
            String update="delete from Event where event=?";
            try{
                prepare=connect.prepareStatement(update);
                prepare.setString(1, event_textarea.getText());
                prepare.executeUpdate();
                showEventData();
                alert2.SuccessMessage("Xoá thành công!");
                eventClear();
                connect.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void eventClear(){
        event_textarea.setText("");
        event_date_starr.setValue(null);
        event_date_end.setValue(null);
        status_combo.getSelectionModel().clearSelection();
    }
    public void comboEvent(){
        List<String> listA= new ArrayList<>();
        for(String data:EventData.status){
            listA.add(data);
        }
        ObservableList ListData=FXCollections.observableArrayList(listA);
        status_combo.setItems(ListData);
    }
    //Trang task
    ////////////////dai phan cạch
    public void addTask() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connect = connectNow.getConnection();
        try{
            if(task_area.getText().isEmpty()||date_task.getValue()==null|| status_task.getSelectionModel().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else{
                String insert="insert into Task(date,list,date_start,status)"+"values(?,?,?,?)";
                prepare=connect.prepareStatement(insert);
                Date date= new Date();
                java.sql.Date sqlDate= new java.sql.Date(date.getTime());
                prepare.setString(1,String.valueOf(sqlDate));
                prepare.setString(2,task_area.getText());
                prepare.setString(3,String.valueOf(date_task.getValue()));
                prepare.setString(4, (String) status_task.getSelectionModel().getSelectedItem());
                prepare.executeUpdate();
                showTaskData();
                alert2.SuccessMessage("Add Task is succesfully!");
                taskClear();
                connect.close();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void comboEventTask(){
        List<String> listB= new ArrayList<>();
        for(String data: TaskData.status){
            listB.add(data);
        }
        ObservableList listData= FXCollections.observableArrayList(listB);
        status_task.setItems(listData);
    }
    //display table small view
    public ObservableList<TaskData> TaskDataSmall(){
        ObservableList<TaskData> listData=FXCollections.observableArrayList();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connect = connectNow.getConnection();
        LocalDate today = LocalDate.now();
        String selectData = "select * from Task where date =?";
        try {
            prepare = connect.prepareStatement(selectData);
            prepare.setDate(1, java.sql.Date.valueOf(today));
            resultSet=prepare.executeQuery();
            TaskData tData;
            while (resultSet.next()){
                tData= new TaskData( resultSet.getString("list"),resultSet.getString("status"));
                listData.add(tData);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listData;
    }
    private ObservableList<TaskData>TaskListSmall;
    public void showTaskSmall(){
        TaskListSmall=TaskDataSmall();
        list_col_table.setCellValueFactory(new PropertyValueFactory<>("List"));
        status_table.setCellValueFactory(new PropertyValueFactory<>("Status"));
        table_today.setItems(TaskListSmall);
        showTaskData();
    }
    //display in table view
    public ObservableList<TaskData> TaskDataList() {
        ObservableList<TaskData> listData = FXCollections.observableArrayList();
        String selectData = "select * from Task";
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connect = connectNow.getConnection();
        try {
            prepare = connect.prepareStatement(selectData);
            resultSet=prepare.executeQuery();
            TaskData tData;
            while (resultSet.next()){
                tData= new TaskData(resultSet.getDate("date_start"), resultSet.getString("list"),resultSet.getString("status"));
                listData.add(tData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    private ObservableList<TaskData>TaskListData;
    public void showTaskData(){
        TaskListData=TaskDataList();
        datetask.setCellValueFactory(new PropertyValueFactory<>("date"));
        todolisttask.setCellValueFactory(new PropertyValueFactory<>("List"));
        status_coll.setCellValueFactory(new PropertyValueFactory<>("Status"));
        table_view_task.setItems(TaskListData);
    }
    private int TaskID;
    public void TaskSelectedData(){
        TaskData tData=table_view_task.getSelectionModel().getSelectedItem();
        int num=table_view_task.getSelectionModel().getSelectedIndex();
        if((num-1)<-1) return;
        TaskID=tData.getId();
        task_area.setText(tData.getList());
        date_task.setValue(LocalDate.parse(String.valueOf(tData.getDate())));
        status_task.setValue((String.valueOf(tData.getStatus())));

    }

    public void taskUpdate() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connect = connectNow.getConnection();

        if ( task_area.getText().isEmpty() || date_task.getValue() == null || status_task.getSelectionModel().isEmpty()) {
            alert2.ErrorMessage("Vui lòng điền đầy đủ thông tin vào các ô!");
            return;
        }

        if (!alert2.ConfirmMessage("Bạn muốn cập nhật công việc này: " + task_area.getText())) {
            return; // Không cập nhật nếu người dùng không xác nhận
        }

        String update = "UPDATE Task SET list=?, date_start=?, status=? WHERE id=?";
        try {
            prepare = connect.prepareStatement(update);
            prepare.setString(1, task_area.getText());
            prepare.setString(2, String.valueOf(date_task.getValue()));
            prepare.setString(3, (String) status_task.getSelectionModel().getSelectedItem());
            prepare.setInt(4, TaskID);

            int affectedRows = prepare.executeUpdate();
            if (affectedRows > 0) {
                alert2.SuccessMessage("Cập nhật thành công!");
                showTaskData(); // Cập nhật lại dữ liệu hiển thị
                taskClear(); // Xóa dữ liệu sau khi cập nhật thành công
            } else {
                alert2.ErrorMessage("Không thể cập nhật công việc.");
            }

            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void taskClear(){
        task_area.setText("");
        date_task.setValue(null);
        status_task.getSelectionModel().clearSelection();
    }
    public void taskDelete(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connect = connectNow.getConnection();
        if(task_area.getText().isEmpty()||
                date_task.getValue()==null||
                status_task.getSelectionModel().getSelectedItem().isEmpty()
        ){
            alert2.ErrorMessage("Vui lòng điền đầy đủ vào các ô!");
        }else{
            if(alert2.ConfirmMessage("Bạn muốn xoá sự kiện này" +task_area.getText()));
            Date date= new Date();
            java.sql.Date sqlDate= new java.sql.Date(date.getTime());
            String update="delete from Task where list=?";
            try{
                prepare=connect.prepareStatement(update);
                prepare.setString(1, task_area.getText());
                prepare.executeUpdate();
                showTaskData();
                alert2.SuccessMessage("Xoá thành công!");
                taskClear();
                connect.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
    // home page
    public void homeDisplayUserName(){
        username.setText(username.getText());
    }
    @FXML
    private Label event_total;
    public void displayEvent(){
        String sql="select count(id) from Event";
        DatabaseConnection connectNow= new DatabaseConnection();
        Connection connectDB= connectNow.getConnection();
        int tempE=0;
        try{
            prepare=connectDB.prepareStatement(sql);
            resultSet= prepare.executeQuery();
            if(resultSet.next()){
                tempE=resultSet.getInt("count(id)");
            }
            event_total.setText(""+tempE);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private Label total_task;
    public void displayTask(){
        String sql="select count(id) from Task";
        DatabaseConnection connectNow= new DatabaseConnection();
        Connection connectDB= connectNow.getConnection();
        int tempA=0;
        try{
            prepare=connectDB.prepareStatement(sql);
            resultSet= prepare.executeQuery();
            if(resultSet.next()){
                tempA=resultSet.getInt("count(id)");
            }
            total_task.setText(""+tempA);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private Label finished_task;
    public void displayFinishEvent(){
        String sql="select count(id) from Event where status='complete'";
        DatabaseConnection connectNow= new DatabaseConnection();
        Connection connectDB= connectNow.getConnection();
        int tempB=0;
        try{
            prepare=connectDB.prepareStatement(sql);
            resultSet= prepare.executeQuery();
            if(resultSet.next()){
                tempB=resultSet.getInt("count(id)");
            }
            finished_task.setText(""+tempB);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

//

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         showEventData();
         comboEvent();
         comboEventTask();
        showTaskData();
//        addCheckBoxListerners();
        homeDisplayUserName();
        displayEvent();
        displayTask();
        displayFinishEvent();
        showTaskSmall();
    }

}
