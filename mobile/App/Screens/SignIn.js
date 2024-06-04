import React, { useState } from 'react';
import { View, Text, TextInput, StyleSheet, TouchableOpacity, Alert, Image, Pressable, Modal } from 'react-native';
import { SafeAreaView } from "react-native-safe-area-context";
import COLORS from '../../assets/Shared/Colors';
import { Ionicons } from "@expo/vector-icons";
import Checkbox from "expo-checkbox";
import { Button } from '../Components/Button';
import { API_BASE_URL } from '../Config/apiConfig';
import { useCustomer } from '../Contexts/CustomerContext';
import AsyncStorage from '@react-native-async-storage/async-storage';

export const SignIn = ({ navigation }) => {
    const [isPasswordShown, setIsPasswordShown] = useState(false);
    const [isChecked, setIsChecked] = useState(false);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [email, setEmail] = useState('');
    const [isVerificationCodeCorrect, setIsVerificationCodeCorrect] = useState(false);
    const [showModal, setShowModal] = useState(false);
    const [verificationCodeSent, setVerificationCodeSent] = useState(false);
    const [verificationCode, setVerificationCode] = useState('');
    const [code, setCode] = useState('');
    const [isChangingPassword, setIsChangingPassword] = useState(false);
    const [showPassword, setShowPassword] = useState(false);
    const [confirmPassword, setConfirmPassword] = useState('');
    const [showConfirmPassword, setShowConfirmPassword] = useState(false);
    const { setCustomer } = useCustomer();

    const toggleChangePassword = () => {
        setIsChangingPassword(!isChangingPassword);
    };

    const openVerificationModal = async () => {
        setShowModal(true);
    };

    const sendVerificationCode = async () => {
        try {
            const response = await fetch(`${API_BASE_URL}/api/changePassword`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    email: email,
                }),
            });
            if (response.ok) {
                const data = await response.json();
                setVerificationCode(data);
                setVerificationCodeSent(true);
            } else {
                Alert.alert('Error', 'Failed to send verification code');
            }
        } catch (error) {
            Alert.alert('Error', 'An error occurred while sending verification code');
        }
    };

    const handleVerifyCode = () => {
        if (code.trim() === verificationCode.toString().trim()) {
            setIsVerificationCodeCorrect(true);
        } else {
            Alert.alert('Error', 'Incorrect verification code');
        }
    };

    const handleChangePassword = async () => {
        if (password !== confirmPassword) {
            Alert.alert('Error', 'Passwords do not match');
            return;
        }
        try {
            const response = await fetch(`${API_BASE_URL}/api/changePassword`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    email,
                    password: password,
                    confirmedPassword: confirmPassword,
                }),
            });
            if (response.ok) {
                Alert.alert('Success', 'Password changed successfully');
                setIsChangingPassword(false);
                setShowModal(false);
            } else {
                Alert.alert('Error', 'Failed to change password');
            }
        } catch (error) {
            Alert.alert('Error', 'An error occurred while changing the password');
        }
    };

    const handleSignIn = async () => {
        try {
            const loginRequest = { username, password };
            const response = await fetch(`${API_BASE_URL}/api/auth/signin`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(loginRequest),
            });
            const data = await response.json();
            if (response.ok) {
                await AsyncStorage.setItem('jwtToken', data.token);
                setCustomer(data);
                navigation.navigate('MainScreen');
            } else {
                Alert.alert('Error', data.message);
            }
        } catch (error) {
            Alert.alert('Error', 'An error occurred during sign in.');
        }
    };

    return (
        <SafeAreaView style={{ flex: 1, backgroundColor: COLORS.while }}>
            <View style={{ flex: 1, marginHorizontal: 22 }}>
                <View style={{ marginVertical: 22 }}>
                    <Text style={{ fontSize: 22, fontWeight: 'bold', marginVertical: 12, color: COLORS.GRAY }}>
                        Hi Welcome Back ! 👋
                    </Text>
                    <Text style={{ fontSize: 16, color: COLORS.GRAY }}>Hello again you have been missed!</Text>
                </View>

                <View style={{ marginBottom: 12 }}>
                    <Text style={{ fontSize: 16, fontWeight: '400', marginVertical: 8 }}>Username</Text>
                    <View style={{ width: "100%", height: 48, borderColor: COLORS.GRAY, borderWidth: 1, borderRadius: 8, alignItems: "center", justifyContent: "center", paddingLeft: 22 }}>
                        <TextInput
                            placeholder='Enter your username'
                            placeholderTextColor={COLORS.GRAY}
                            style={{ width: "100%" }}
                            value={username}
                            onChangeText={text => setUsername(text)}
                        />
                    </View>
                </View>

                <View style={{ marginBottom: 12 }}>
                    <Text style={{ fontSize: 16, fontWeight: '400', marginVertical: 8 }}>Password</Text>
                    <View style={{ width: "100%", height: 48, borderColor: COLORS.GRAY, borderWidth: 1, borderRadius: 8, alignItems: "center", justifyContent: "center", paddingLeft: 22 }}>
                        <TextInput
                            placeholder='Enter your password'
                            placeholderTextColor={COLORS.GRAY}
                            secureTextEntry={!isPasswordShown}
                            style={{ width: "100%" }}
                            value={password}
                            onChangeText={text => setPassword(text)}
                        />
                        <TouchableOpacity
                            onPress={() => setIsPasswordShown(!isPasswordShown)}
                            style={{ position: "absolute", right: 12 }}
                        >
                            {
                                isPasswordShown ? (
                                    <Ionicons name="eye" size={24} color={COLORS.GRAY} />
                                ) : (
                                    <Ionicons name="eye-off" size={24} color={COLORS.GRAY} />
                                )
                            }
                        </TouchableOpacity>
                    </View>
                </View>

                <View style={{ flexDirection: 'row', marginVertical: 6 }}>
                    <Checkbox
                        style={{ marginRight: 8 }}
                        value={isChecked}
                        onValueChange={setIsChecked}
                        color={isChecked ? COLORS.PRIMARY : undefined}
                    />
                    <Text>Remember Me</Text>
                </View>

                <Button
                    title="Sign In"
                    filled
                    onPress={handleSignIn}
                    style={{ marginTop: 18, marginBottom: 4 }}
                />

                <TouchableOpacity onPress={openVerificationModal} style={[styles.iconButton, { alignItems: 'center' }]}>
                   <Ionicons name="key" size={24} color="#007BFF" />
                   <Text style={{color: '#007BFF', marginLeft: 8 }}>Forget Password ?</Text>
             </TouchableOpacity>

                <Modal
                    animationType="slide"
                    transparent={true}
                    visible={showModal}
                    onRequestClose={() => setShowModal(false)}
                >
                    <View style={styles.modalContainer}>
                        <View style={styles.modalContent}>
                            {!verificationCodeSent ? (
                                <>
                                    <Text style={styles.modalText}>Enter your email to receive a verification code:</Text>
                                    <View style={styles.passwordContainer}>
                                    <TextInput
                                        style={styles.input}
                                        placeholder="Email"
                                        onChangeText={text => setEmail(text)}
                                        value={email}
                                    />
                                       </View>
                                    <TouchableOpacity style={styles.modalButton} onPress={sendVerificationCode}>
                                        <Text style={styles.buttonText}>Send Verification Code</Text>
                                    </TouchableOpacity>
                                </>
                            ) : !isVerificationCodeCorrect ? (
                                <>
                                    <Text style={styles.modalText}>Enter verification code:</Text>
                                    <TextInput
                                        style={styles.input}
                                        placeholder="Verification Code"
                                        onChangeText={text => setCode(text)}
                                        value={code}
                                    />
                                    <TouchableOpacity style={styles.modalButton} onPress={handleVerifyCode}>
                                        <Text style={styles.buttonText}>Submit</Text>
                                    </TouchableOpacity>
                                </>
                            ) : (
                                <>
                                    <Text style={styles.modalText}>Enter your new password:</Text>
                                    <View style={styles.passwordContainer}>
                                        <TextInput
                                            style={styles.input}
                                            placeholder="New Password"
                                            secureTextEntry={!showPassword}
                                            onChangeText={setPassword}
                                        />
                                        <TouchableOpacity style={styles.showHideButton} onPress={() => setShowPassword(!showPassword)}>
                                            <Ionicons name={showPassword ? 'eye' : 'eye-off'} size={24} color={COLORS.GRAY} />
                                        </TouchableOpacity>
                                    </View>
                                    <View style={styles.passwordContainer}>
                                        <TextInput
                                            style={styles.input}
                                            placeholder="Confirm New Password"
                                            secureTextEntry={!showConfirmPassword}
                                            onChangeText={setConfirmPassword}
                                        />
                                        <TouchableOpacity style={styles.showHideButton} onPress={() => setShowConfirmPassword(!showConfirmPassword)}>
                                            <Ionicons name={showConfirmPassword ? 'eye' : 'eye-off'} size={24} color={COLORS.GRAY} />
                                        </TouchableOpacity>
                                    </View>
                                    <TouchableOpacity style={styles.modalButton} onPress={handleChangePassword}>
                                        <Text style={styles.buttonText}>Change Password</Text>
                                    </TouchableOpacity>
                                </>
                            )}
                        </View>
                    </View>
                </Modal>

                <View style={{ flexDirection: 'row', alignItems: 'center', marginVertical: 20 }}>
                    <View style={{ flex: 1, height: 1, backgroundColor: COLORS.LIGHT_GRAY, marginHorizontal: 10 }} />
                    <Text style={{ fontSize: 14 }}>Or SignIn with</Text>
                    <View style={{ flex: 1, height: 1, backgroundColor: COLORS.LIGHT_GRAY, marginHorizontal: 10 }} />
                </View>

                <View style={{ flexDirection: 'row', justifyContent: 'center' }}>
                    <TouchableOpacity
                        onPress={() => console.log("Pressed")}
                        style={{ flex: 1, alignItems: 'center', justifyContent: 'center', flexDirection: 'row', height: 52, borderWidth: 1, borderColor: COLORS.LIGHT_GRAY, marginRight: 4, borderRadius: 10 }}
                    >
                        <Image
                            source={require("../../assets/Images/facebook.png")}
                            style={{ height: 36, width: 36, marginRight: 8 }}
                            resizeMode='contain'
                        />
                        <Text>Facebook</Text>
                    </TouchableOpacity>

                    <TouchableOpacity
                        onPress={() => console.log("Pressed")}
                        style={{ flex: 1, alignItems: 'center', justifyContent: 'center', flexDirection: 'row', height: 52, borderWidth: 1, borderColor: COLORS.LIGHT_GRAY, borderRadius: 10 }}
                    >
                        <Image
                            source={require("../../assets/Images/google.png")}
                            style={{ height: 36, width: 36, marginRight: 8 }}
                            resizeMode='contain'
                        />
                        <Text>Google</Text>
                    </TouchableOpacity>
                </View>

                <View style={{ flexDirection: "row", justifyContent: "center", marginVertical: 22 }}>
                    <Text style={{ fontSize: 16, color: COLORS.GRAY }}>Don't have an account ? </Text>
                    <Pressable
                        onPress={() => navigation.navigate("EmailVerification")}
                    >
                        <Text style={{ fontSize: 16, color: COLORS.PRIMARY, fontWeight: "bold", marginLeft: 6 }}>Register</Text>
                    </Pressable>
                </View>
            </View>
        </SafeAreaView>
    );
};

const styles = StyleSheet.create({
    iconButton: {
        paddingHorizontal: 20,
    },
    modalContainer: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: 'rgba(0, 0, 0, 0.5)',
    },
    modalContent: {
        backgroundColor: '#fff',
        padding: 20,
        borderRadius: 10,
        alignItems: 'center',
    },
    modalText: {
        fontSize: 16,
        marginBottom: 10,
    },
    modalButton: {
        backgroundColor: '#007BFF',
        paddingVertical: 10,
        paddingHorizontal: 20,
        borderRadius: 5,
    },
    buttonText: {
        color: '#fff',
        fontWeight: 'bold',
    },
    input: {
        width: '80%',
        height: 40,
        borderWidth: 1,
        borderColor: '#CCCCCC',
        borderRadius: 5,
        paddingHorizontal: 10,
        marginBottom: 10,
    },
    changePasswordForm: {
        marginTop: 20,
        width: '80%',
    },
    passwordContainer: {
        flexDirection: 'row',
        alignItems: 'center',
    },
    showHideButton: {
        position: 'absolute',
        right: 10,
    },
});


