package com.barbertime.barbertime_backend.notifications;

import com.barbertime.barbertime_backend.entities.Appointment;
import com.barbertime.barbertime_backend.repositories.AppointmentRepository;
import com.barbertime.barbertime_backend.security.Email.EmailSenderService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentService {
    private AppointmentRepository appointmentRepository;
    private EmailSenderService emailSenderService;

    @Scheduled(cron = "0 0 18 * * *")
    public void checkAppointments() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        List<Appointment> appointments = appointmentRepository.findByDate(tomorrow);
        for (Appointment appointment : appointments) {
            String toEmail = appointment.getCustomer().getEmail();
            String subject = "Rappel de rendez-vous";
            String body = "Cher(e) " + appointment.getCustomer().getFirstName() + " " + appointment.getCustomer().getLastName() + ",\n\n"
                    + "Ceci est un rappel que vous avez un rendez-vous prévu demain à " + appointment.getTime() + ".\n\n"
                    + "Voici les détails de votre rendez-vous :\n"
                    + "Date : " + appointment.getDate() + "\n"
                    + "Heure : " + appointment.getTime() + "\n"
                    + "Lieu : " + appointment.getBarberShop().getAddress() + "\n\n"
                    + "Si vous avez besoin de modifier ou d'annuler votre rendez-vous, veuillez nous contacter dès que possible.\n\n"
                    + "Cordialement,\n\n"
                    + "BarberTime\n\n";
            emailSenderService.sendEmail(toEmail, subject, body);
        }
    }
}
