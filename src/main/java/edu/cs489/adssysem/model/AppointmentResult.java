package edu.cs489.adssysem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResult {
    private String dentistName;
    private Integer patNo;
    private String patName;
    private String appointmentDateTime;
    private Integer surgeryNo;


    @Override
    public String toString() {
        return  dentistName +
                "\t\t\t" + patNo +
                "\t\t\t" + patName +
                "\t\t\t" + appointmentDateTime +
                "\t\t\t" + surgeryNo;
    }
}
