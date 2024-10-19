package com.Sabreen.devices_price_Mclassification.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Device {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
// Numerical fields
private Float battery_power; // Corresponds to 'battery_power' in Flask
private Float clock_speed; // Corresponds to 'clock_speed' in Flask
private Integer fc; // Corresponds to 'fc' in Flask
private Integer int_memory; // Corresponds to 'int_memory' in Flask
private Float m_dep; // Corresponds to 'm_dep' in Flask
private Integer mobile_wt; // Corresponds to 'mobile_wt' in Flask
private Integer n_cores; // Corresponds to 'n_cores' in Flask
private Integer pc; // Corresponds to 'pc' in Flask
private Integer px_height; // Corresponds to 'px_height' in Flask
private Integer px_width; // Corresponds to 'px_width' in Flask
private Integer ram; // Corresponds to 'ram' in Flask
private Float sc_h; // Corresponds to 'sc_h' in Flask
private Float sc_w; // Corresponds to 'sc_w' in Flask
private Integer talk_time; // Corresponds to 'talk_time' in Flask

// Boolean fields (stored as small integers in the database, 0 or 1)
private Boolean blue; // Corresponds to 'blue' in Flask
private Boolean dual_sim; // Corresponds to 'dual_sim' in Flask
private Boolean four_g; // Corresponds to 'four_g' in Flask
private Boolean three_g; // Corresponds to 'three_g' in Flask
private Boolean touch_screen; // Corresponds to 'touch_screen' in Flask
private Boolean wifi; // Corresponds to 'wifi' in Flask

    // Field to store the predicted price
    private int price_range;

    // Set method for predicted price
    public void setPredictedPrice(int price_range) {
        this.price_range = price_range;
    }

    public void setPredictedPrice(String predictedPrice) {

        try {
            this.price_range = Integer.parseInt(predictedPrice);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing predicted price: " + e.getMessage());
        }
    }

}
