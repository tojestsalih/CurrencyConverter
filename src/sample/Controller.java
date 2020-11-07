package sample;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class Controller {
    @FXML
    private TextField mainCurrency;//amount of usd
    @FXML
    private TextField newCurrency;//new amount of money
    @FXML
    private ComboBox<String> comboBoxValue;//comboBox
    @FXML
    private Label label;//show 1 usd to counter currency
    //

    public void onConvertButton(){
        String boxValue = comboBoxValue.getSelectionModel().getSelectedItem();//gettin counter CURRENCY NAME from combobox
        ApiOperations apiOperations = new ApiOperations();//create object to use functions

        float new_currency = apiOperations.convert(boxValue);//getting counter CURRENCY AMOUNT
        float intermediateValue= Integer.parseInt( mainCurrency.getText())*new_currency;//1-get base CURRENCY AMOUNT
        // 2-create interm.Value for easy to read
        //multiplation base currency and counter currency
        newCurrency.setText(String.valueOf(intermediateValue) + "  " + boxValue);//Screen the new value
        label.setText("1 USD = " + new_currency + " " + boxValue );

//        System.out.println(apiOperations.getApiKey());//testing

    }
}
