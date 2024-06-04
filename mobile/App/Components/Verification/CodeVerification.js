import React, { useState } from 'react';
import { View, Text, TextInput, TouchableOpacity, Alert, StyleSheet } from 'react-native';

export const CodeVerification = ({ route, navigation }) => {
    const { email, verificationCode } = route.params;
    const [code, setCode] = useState('');

    const handleVerifyCode = () => {
        const trimmedCode = code.trim();
        const trimmedVerificationCode = verificationCode.toString().trim();

        console.log(`Verification code from route params: ${trimmedVerificationCode}`);
        console.log(`User entered code: ${trimmedCode}`);
        console.log(`Email: ${email}`);

        if (trimmedCode === trimmedVerificationCode) {
            Alert.alert('Success', 'Code verified');
            navigation.navigate('SignUp', { email });
        } else {
            Alert.alert('Error', 'Invalid verification code');
        }
    };

    return (
        <View style={styles.container}>
            <Text style={styles.label}>Verification Code</Text>
            <TextInput
                style={styles.input}
                placeholder="Enter the code"
                onChangeText={setCode}
                value={code}
                keyboardType="numeric"
            />
            <TouchableOpacity style={styles.button} onPress={handleVerifyCode}>
                <Text style={styles.buttonText}>Verify Code</Text>
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
