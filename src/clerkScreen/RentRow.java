package clerkScreen;

import javafx.beans.property.SimpleStringProperty;

public class RentRow {
    
    private SimpleStringProperty rentID;
    private SimpleStringProperty vehicleMake;
    private SimpleStringProperty vehicleModel;
    private SimpleStringProperty licensePlate;
    private SimpleStringProperty rentStart;
    private SimpleStringProperty rentEnd;
    private SimpleStringProperty equipment;
    
    /**
     * Rent row constructor.
     * Represents a single row in the list of current rentals.
     */
    public RentRow(String rentID, String make, String model, String plate, String start, String end) 
    {
        this.rentID = new SimpleStringProperty(rentID);
        this.vehicleMake = new SimpleStringProperty(make);
        this.vehicleModel = new SimpleStringProperty(model);
        this.licensePlate = new SimpleStringProperty(plate);
        this.rentStart = new SimpleStringProperty(start);
        this.rentEnd = new SimpleStringProperty(end);
        this.equipment = new SimpleStringProperty();
    }

    /**
     * Sets the equipment Type
     * @pre equipmentType.length > 0
     * @post equipment.value = equipmentType
     * @param equipmentType
     */
    public void setEquipmentType(String equipmentType) {
        this.equipment.set(equipmentType);
    }
    
    public String getEquipmentType() {
        if (equipment.get().isEmpty())
        {
            return "N/A";
        }
        return equipment.get();
    }
    public String getRentID() {
        return rentID.get();
    }

    public String getVehicleMake() {
        return vehicleMake.get();
    }

    public String getVehicleModel() {
        return vehicleModel.get();
    }

    public String getLicensePlate() {
        return licensePlate.get();
    }

    public String getRentStart() {
        return rentStart.get();
    }

    public String getRentEnd() {
        return rentEnd.get();
    }
    
    public String getEquipment() {
        return equipment.get();
    }

}
