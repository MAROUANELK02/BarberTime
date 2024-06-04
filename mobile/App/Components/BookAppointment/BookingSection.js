import React, { useEffect, useState } from 'react';
import { View, Text, FlatList, StyleSheet, TextInput, TouchableOpacity } from 'react-native';
import Colors from '../../../assets/Shared/Colors';
import moment from 'moment';
import { API_BASE_URL } from '../../Config/apiConfig';
import { Alert } from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';


export function BookingSection({ barber, customer }) {
    const [next15Days, setNext15Days] = useState([]);
    const [timeList, settimeList] = useState([]);
    const [selectedDate, setSelectedDate] = useState(null);
    const [selectedTime, setSelectedTime] = useState(null);
    const [selectedService, setSelectedService] = useState(null); 

    useEffect(() => {
    
        getDays();
        getTime();
    }, []);

    const getDays = () => {
        const nextDays = [];
        for (let i = 1; i < 16; i++) {
            const date = moment().add(i, 'days');
            const dayOfWeek = date.format('dddd');
            const dayOff = barber?.dayOff === dayOfWeek;
            nextDays.push({
                date: date,
                day: date.format('ddd'), 
                formattedDate: date.format('Do MMM'), 
                isDisabled: dayOff, 
            });
        }
        setNext15Days(nextDays);
    };

    const getTime = () => {
        const timeList = [];
        for (let i = 8; i <= 20; i++) {
            timeList.push({ time: i + ':00' });
            timeList.push({ time: i + ':30' });
        }
        
        settimeList(timeList);
    };

    const handleDayPress = (item) => {
        if (!item.isDisabled) {
            setSelectedDate(item.date);
            setSelectedTime(null);
        }
    };

    const handleTimePress = (item) => {
        if (selectedDate && !item.isDisabled) {
            setSelectedTime(item.time);
        }
    };

    const handleServicePress = (service) => {
        setSelectedService(service);
    };

    const handleMakeAppointment = async () => {
        if (selectedDate && selectedTime && selectedService) {
            const idCustomer = customer.idUser;
            const idBarber = barber.idBarberShop;
    
            const formattedTime = moment(selectedTime, 'HH:mm').format('HH:mm:ss');
    
            const appointmentReqDTO = {
                date: moment(selectedDate).format('YYYY-MM-DD'),
                time: formattedTime,
                service: selectedService.serviceName,
            };
    
            const token = await AsyncStorage.getItem('jwtToken');
            fetch(`${API_BASE_URL}/api/customer/appointment?idCustomer=${idCustomer}&idBarber=${idBarber}`, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(appointmentReqDTO),
            })
            .then(response => {
                if (!response.ok) {
                    return response.json().then(errorData => {
                        throw new Error(errorData.message);
                    });
                }
                return response.json();
            })
            .then(data => {
                console.log('Appointment successfully saved:', data);
                Alert.alert('Success', 'Appointment successfully saved!');
            })
            .catch(error => {
          
                if (error.message === 'Barber shop is full on this time') {
                    Alert.alert('Sorry', 'The barber shop is fully booked for the selected time. Please choose a different time.');
                } 
               else if(error.message === 'Barber shop is closed on this time'){
                Alert.alert('Sorry', 'The barber shop is closed on this time. Please choose a different time.');
               }
               else if(error.message === 'You already have an appointment on this time'){
                Alert.alert('Sorry', 'The barber shop is closed on this time. Please choose a different time.');
               }
               else if(error.message === 'Holiday on this date'){
                Alert.alert('Sorry', 'There are holidays on this date. Please choose a different time.');
               }
                else {
                    Alert.alert('Error', 'An error occurred while saving the appointment. Please try again later.');
                }
            });
        } else {
            Alert.alert('Error', 'Please select a date, time, and service before making an appointment.');
        }
    };

    return (
        <View>
            <Text style={[styles.text, { marginTop: -10 }]}>Day</Text>
            <FlatList
                data={next15Days}
                horizontal={true}
                showsHorizontalScrollIndicator={false}
                renderItem={({ item }) => (
                    <TouchableOpacity
                        style={[
                            styles.dayButton,
                            item.isDisabled && styles.disabledButton,
                            selectedDate === item.date && styles.selectedButton,
                        ]}
                        onPress={() => handleDayPress(item)}
                        disabled={item.isDisabled}
                    >
                        <Text style={[{ fontFamily: 'Roboto' }, selectedDate === item.date && { color: Colors.while }]}>
                            {item.day}
                        </Text>
                        <Text style={[{ fontFamily: 'Roboto', fontSize: 16 }]}>
                            {item.formattedDate}
                        </Text>
                    </TouchableOpacity>
                )}
            />

            <Text style={styles.text}>Time</Text>
            <FlatList
                data={timeList}
                horizontal={true}
                showsHorizontalScrollIndicator={false}
                renderItem={({ item }) => (
                    <TouchableOpacity
                        style={[
                            styles.dayButton,
                            selectedTime === item.time && styles.selectedButton,
                        ]}
                        onPress={() => handleTimePress(item)}
                        disabled={!selectedDate || item.isDisabled}
                    >
                        <Text style={[{ fontFamily: 'Roboto', fontSize: 16 }, selectedTime === item.time && { color: Colors.while }]}>
                            {item.time}
                        </Text>
                    </TouchableOpacity>
                )}
            />

<Text style={styles.text}>Service</Text>

    <FlatList
        data={barber?.services}
        horizontal={true}
        showsHorizontalScrollIndicator={false}
        keyExtractor={(item) => item.idService.toString()}
        renderItem={({ item }) => (
            <TouchableOpacity
                style={[
                    styles.dayButton,
                    selectedService && selectedService.idService === item.idService && styles.selectedButton,
                ]}
                onPress={() => handleServicePress(item)}
            >
                <Text style={[{ fontFamily: 'Roboto', fontSize: 16 }]}>
                    {item.serviceName} - {item.price}Dhs
                </Text>
            </TouchableOpacity>
        )}
    />



            <Text style={styles.text}>Note</Text>
            <TextInput
                numberOfLines={3}
                style={styles.textInput}
                placeholder='Write Notes Here'
            />

            <TouchableOpacity
                onPress={handleMakeAppointment}
                style={[styles.button, { backgroundColor: !selectedDate || !selectedTime || !selectedService ? Colors.GRAY : Colors.PRIMARY }]}
                disabled={!selectedDate || !selectedTime || !selectedService}
            >
                <Text style={styles.buttonText}>Make Appointment</Text>
            </TouchableOpacity>
        </View>
    );
}

const styles = StyleSheet.create({
    dayButton: {
        borderWidth: 1,
        borderRadius: 99,
        padding: 5,
        borderColor: Colors.GRAY,
        paddingHorizontal: 20,
        alignItems: 'center',
        marginRight: 10,
    },
    disabledButton: {
        opacity: 0.5,
    },
    selectedButton: {
        backgroundColor: Colors.PRIMARY,
    },
    textInput: {
        backgroundColor: Colors.LIGHT_GRAY,
        padding: 10,
        borderRadius: 10,
        borderColor: Colors.SECONDARY,
        borderWidth: 1,
        textAlignVertical: 'top',
        marginBottom: 10,
    },
    button: {
        padding: 13,
        backgroundColor: Colors.PRIMARY,
        margin: 10,
        borderRadius: 99,
    },
    buttonText: {
        color: Colors.while,
        textAlign: 'center',
        fontSize: 17,
    },
    text: {
        fontSize: 18,
        marginBottom: 5,
        marginTop: 5,
    },
});
