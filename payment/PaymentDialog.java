package payment;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import databaseManagement.Query;

public class PaymentDialog {
    
    private String creditCardNo;
    private String cardType;
    
    private TextField paymentAmt;
    private double paidAmt;
    private int points;
    
    private GridPane thisPane;
    
    public PaymentDialog(String rentID) 
    {
        
        getCreditCardInfo(rentID);
        
        thisPane = new GridPane();
        thisPane.setHgap(10);
        thisPane.setVgap(10);
        thisPane.setPadding(new Insets(0, 10, 0, 10));
        paymentAmt = new TextField();
        thisPane.add(new Label("Card Type"), 0, 0);
        thisPane.add(new Label(cardType), 1, 0);
        thisPane.add(new Label("Card Number"), 0, 1);
        thisPane.add(new Label(creditCardNo), 1, 1);
        thisPane.add(new Label("Payment Amount: "), 0, 3);
        thisPane.add(paymentAmt, 1, 3);
        
        
        
    }
    
    public GridPane getWindow() {
        return thisPane;
    }
    
    /**
     * Get the credit card information for the specified rent.
     */
    private void getCreditCardInfo(String rentID)
    {
        try {
            ResultSet result = Query.select("SELECT cardNo, cardType FROM rentCardInfo WHERE rentID='" + rentID +"'" );
            if (!result.next())
            {
                System.out.println("No results!");
                return;
            }
            creditCardNo = result.getString(1);
            cardType = result.getString(2);
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Callback method
     * @return Callback method for the dialog
     */
    public Callback<Void, Void> paymentCallback() {
        return new Callback<Void, Void>() {
           @Override
            public Void call(Void param) 
            {
                String amount = paymentAmt.getText();
                if (!amount.isEmpty())
                {
                    paidAmt = Double.parseDouble(amount);
                }
                return null;
            }
        };
    }
    
    public double getPaidAmount() {
        return paidAmt;
    }
    
    public int getPoints() {
        return points;
    }
    
    

}
