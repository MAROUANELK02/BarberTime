import React, { useState } from 'react';
import { View, Text, TextInput, TouchableOpacity, Alert, StyleSheet } from 'react-native';
import { API_BASE_URL } from '../../Config/apiConfig';

export const EmailVerification = ({ navigation }) => {
    const [email, setEmail] = useState('');

    const handleSendEmail = async () => {
        try {
            const response = await fetch(`${API_BASE_URL}/api/register`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ email }),
            });

            if (response.ok) {
                const data = await response.json();
                const verificationCode = data.verificationCode;
                console.log(verificationCode);
                Alert.alert('Success', 'Verification code sent to your email');
                navigation.navigate('CodeVerification', { email, verificationCode });
            } else {
                const errorData = await response.json();
                Alert.alert('Error', errorData.message || 'Failed to send email');
            }
        } catch (error) {
            Alert.alert('Error', 'Failed to send email');
        }
    };

    return (
        <View style={styles.container}>
            <Text style={styles.label}>Email</Text>
            <TextInput
                style={styles.input}
                placeholder="Enter your email"
                onChangeText={setEmail}
                value={email}
                keyboardType="email-address"
            />
            <TouchableOpacity style={styles.button} onPress={handleSendEmail}>
                <Text style={styles.buttonText}>Send Verification Code</Text>
            </TouchableOpacity>
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        padding: 16,
        backgroundColor: '#fff',
    },
    label: {
        fontSize: 16,
        marginBottom: 8,
    },
    input: {
        borderWidth: 1,
        borderColor: '#ccc',
        borderRadius: 8,
        padding: 12,
        marginBottom: 16,
    },
    button: {
        backgroundColor: '#007BFF',
        padding: 16,
        borderRadius: 8,
        alignItems: 'center',
    },
    buttonText: {
        color: '#fff',
        fontSize: 16,
    },
});
