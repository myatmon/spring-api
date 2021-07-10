package sg.mms.springapi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_appliance", uniqueConstraints = {@UniqueConstraint(columnNames = {"serial_number", "brand", "model"})})
public class Appliance implements Serializable {
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

    @Column(name = "status")
    private String status;

    @Column(name = "dateBought")
    private LocalDate dateBought;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDateBought() {
        return dateBought;
    }

    public void setDateBought(LocalDate dateBought) {
        this.dateBought = dateBought;
    }
    
    @Override
    public String toString() {
        return "Appliance [id=" + id + ", serialNumber=" + serialNumber + ", brand=" + brand + ", model=" + model + ", status="
                + status + ", dateBought="
                + dateBought + "]";
    }
}
