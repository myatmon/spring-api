package sg.mms.springapi.model;

import javax.persistence.*;

@Entity
@Table(name = "tbl_appliance")
public class Appliance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    @Override
    public String toString() {
        return "Appliance [id=" + id + ", serialNumber=" + serialNumber + ", brand=" + brand + ", model=" + model
                + "]";
    }
}
