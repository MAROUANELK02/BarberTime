import React, { useState } from 'react';
import { View, Text, TextInput, TouchableOpacity, Alert, StyleSheet, ScrollView , Image} from 'react-native';
import { API_BASE_URL } from '../Config/apiConfig';
import Checkbox from "expo-checkbox";
import { SafeAreaView } from "react-native-safe-area-context";
import { Ionicons } from "@expo/vector-icons";
import COLORS from '../../assets/Shared/Colors';
export  function SignUp({ route, navigation }){
    const { email } = route.params;
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [username, setUsername] = useState('');
    const [telNumber, setTelNumber] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [isChecked, setIsChecked] = useState(false);
    const [telNumberPrefix, settelNumberPrefix] = useState('+');
    const [telNumberSuffix, settelNumberSuffix] = useState('');
    const [showPassword, setShowPassword] = useState(false);
    const [showConfirmPassword, setShowConfirmPassword] = useState(false);


    const handleFocus = () => {
        if (!telNumberPrefix.startsWith('+')) {
            settelNumberPrefix('+');
        }
    };
    const handleSignUp = async () => {
        if (!firstName || !lastName || !username || !telNumberSuffix || !password || !confirmPassword) {
            Alert.alert('Erreur', 'Veuillez remplir tous les champs');
            return;
        }

        if (password.length < 6) {
            Alert.alert('Erreur', 'Le mot de passe doit avoir au moins 6 caractères');
            return;
        }

        if (password !== confirmPassword) {
            Alert.alert('Erreur', 'Les mots de passe ne correspondent pas');
            return;
        }

        if (!isChecked) {
            Alert.alert('Erreur', 'Veuillez accepter les termes et conditions');
            return;
        }

        try {
            const response = await fetch(`${API_BASE_URL}/api/createCustomer`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    firstName: firstName,
                    lastName: lastName,
                    email,
                    telNumber: telNumberPrefix + telNumberSuffix,
                    username: username,
                    password: password,
                }),
            });

            if (!response.ok) {
                throw new Error('Une erreur est survenue lors de la création du compte');
            }

            const data = await response.json();
            Alert.alert('Success', 'Account created successfully');
            navigation.navigate('Login'); // Assuming you have a login screen to navigate to
        } catch (error) {
            Alert.alert('Erreur', error.message);
        }
    };

    return (
        <ScrollView style={{ flex: 1, backgroundColor: COLORS.while }}>
            <View style={{ flex: 1, marginHorizontal: 22 }}>
                <View style={{ marginTop: -20 }}>
                    <Text style={{
                        fontSize: 22,
                        fontWeight: 'bold',
                        marginVertical: 12,
                        color: COLORS.GRAY
                    }}>
                        Create Account
                    </Text>

                    <Text style={{
                        fontSize: 16,
                        color: COLORS.GRAY
                    }}>Connect with your friend today!</Text>
                </View>

                <View style={{ marginBottom: 12 }}>
                    <Text style={{
                        fontSize: 16,
                        fontWeight: '400',
                        marginVertical: 8
                    }}>First Name</Text>

                    <View style={{
                        width: "100%",
                        height: 48,
                        borderColor: COLORS.GRAY,
                        borderWidth: 1,
                        borderRadius: 8,
                        alignItems: "center",
                        justifyContent: "center",
                        paddingLeft: 22
                    }}>
                        <TextInput
                            placeholder='Enter your first name'
                            placeholderTextColor={COLORS.GRAY}
                            onChangeText={setFirstName}
                            style={{
                                width: "100%"
                            }}
                        />
                    </View>
                </View>

                <View style={{ marginBottom: 12 }}>
                    <Text style={{
                        fontSize: 16,
                        fontWeight: '400',
                        marginVertical: 8
                    }}>Last Name</Text>

                    <View style={{
                        width: "100%",
                        height: 48,
                        borderColor: COLORS.GRAY,
                        borderWidth: 1,
                        borderRadius: 8,
                        alignItems: "center",
                        justifyContent: "center",
                        paddingLeft: 22
                    }}>
                        <TextInput
                            placeholder='Enter your last name'
                            placeholderTextColor={COLORS.GRAY}
                            onChangeText={setLastName}
                            style={{
                                width: "100%"
                            }}
                        />
                    </View>
                </View>

                <View style={{ marginBottom: 12 }}>
                    <Text style={{
                        fontSize: 16,
                        fontWeight: '400',
                        marginVertical: 8
                    }}>Username</Text>

                    <View style={{
                        width: "100%",
                        height: 48,
                        borderColor: COLORS.GRAY,
                        borderWidth: 1,
                        borderRadius: 8,
                        alignItems: "center",
                        justifyContent: "center",
                        paddingLeft: 22
                    }}>
                        <TextInput
                            placeholder='Enter a username'
                            placeholderTextColor={COLORS.GRAY}
                            onChangeText={setUsername}
                            style={{
                                width: "100%"
                            }}
                        />
                    </View>
                </View>

              

                <View style={{ marginBottom: 12 }}>
                    <Text style={{
                        fontSize: 16,
                        fontWeight: '400',
                        marginVertical: 8
                    }}>Mobile Number</Text>

                    <View style={{
                        width: "100%",
                        height: 48,
                        borderColor: COLORS.GRAY,
                        borderWidth: 1,
                        borderRadius: 8,
                        alignItems: "center",
                        flexDirection: "row",
                        justifyContent: "space-between",
                        paddingLeft: 22
                    }}>
                        <TextInput
                            placeholder={telNumberPrefix}
                            placeholderTextColor={COLORS.GRAY}
                            keyboardType='numeric'
                            onFocus={handleFocus}
                            onChangeText={settelNumberPrefix}
                            value={telNumberPrefix}
                            style={{
                                width: "14%",
                                borderRightWidth: 1,
                                borderLeftColor: COLORS.LIGHT_GRAY,
                                height: "100%"
                            }}
                        />

                        <TextInput
                            placeholder='Enter your tel number'
                            placeholderTextColor={COLORS.GRAY}
                            keyboardType='numeric'
                            onChangeText={settelNumberSuffix}
                            value={telNumberSuffix}
                            style={{
                                width: "80%"
                            }}
                        />
                    </View>
                </View>
    
                <View style={{ marginBottom: 12 }}>
                    <Text style={{
                        fontSize: 16,
                        fontWeight: '400',
                        marginVertical: 8
                    }}>Password</Text>
    
                    <View style={{
                        width: "100%",
                        height: 48,
                        borderColor: COLORS.GRAY,
                        borderWidth: 1,
                        borderRadius: 8,
                        alignItems: "center",
                        justifyContent: "center",
                        paddingLeft: 22
                    }}>
                         <View style={styles.passwordContainer}>
                        <TextInput
                            placeholder='Enter your password'
                            placeholderTextColor={COLORS.GRAY}
                            secureTextEntry={!showPassword}
                            onChangeText={setPassword}
                          
                            style={{
                                width: "100%"
                            }}
                        />
                          <TouchableOpacity style={styles.showHideButton} onPress={() => setShowPassword(!showPassword)}>
                <Ionicons name={showPassword ? 'eye' : 'eye-off'} size={24} color={COLORS.GRAY} />
              </TouchableOpacity>
                    </View>
                </View>
              
                </View>
                <View style={{ marginBottom: 12 }}>
                    <Text style={{
                        fontSize: 16,
                        fontWeight: '400',
                        marginVertical: 8
                    }}>Confirm Password</Text>
    
                    <View style={{
                        width: "100%",
                        height: 48,
                        borderColor: COLORS.GRAY,
                        borderWidth: 1,
                        borderRadius: 8,
                        alignItems: "center",
                        justifyContent: "center",
                        paddingLeft: 22
                    }}>
                        <View style={styles.passwordContainer}>
                        <TextInput
                            placeholder='Confirm your password'
                            placeholderTextColor={COLORS.GRAY}
                            secureTextEntry={!showConfirmPassword}
                            onChangeText={setConfirmPassword}
                            style={{
                                width: "100%"
                            }}
                            
                        />
 <TouchableOpacity style={styles.showHideButton} onPress={() => setShowConfirmPassword(!showConfirmPassword)}>
                <Ionicons name={showConfirmPassword ? 'eye' : 'eye-off'} size={24} color={COLORS.GRAY} />
              </TouchableOpacity>
                          </View>
                    </View>
                </View>
    
                <View style={{
                    flexDirection: 'row',
                    marginVertical: 6
                }}>
                    <Checkbox
                        style={{ marginRight: 8 }}
                        value={isChecked}
                        onValueChange={setIsChecked}
                        color={isChecked ? COLORS.PRIMARY : undefined}
                    />
    
                    <Text>I aggree to the terms and conditions</Text>
                </View>
    
                <TouchableOpacity
                    onPress={handleSignUp}
                    style={{
                        backgroundColor: COLORS.PRIMARY,
                        height: 50,
                        alignItems: 'center',
                        justifyContent: 'center',
                        borderRadius: 8,
                        marginTop: 18,
                    }}
                >
                    <Text style={{ color: COLORS.while, fontSize: 18 }}>Sign Up</Text>
                </TouchableOpacity>
    
                <View style={{ flexDirection: 'row', alignItems: 'center', marginVertical: 20 }}>
                    <View
                        style={{
                            flex: 1,
                            height: 1,
                            backgroundColor: COLORS.GRAY,
                            marginHorizontal: 10
                        }}
                    />
                    <Text style={{ fontSize: 14 }}>Or Sign up with</Text>
                    <View
                        style={{
                            flex: 1,
                            height: 1,
                            backgroundColor: COLORS.GRAY,
                            marginHorizontal: 10
                        }}
                    />
                </View>
    
                <View style={{
                    flexDirection: 'row',
                    justifyContent: 'center'
                }}>
                    <TouchableOpacity
                        onPress={() => console.log("Pressed")}
                        style={{
                            flex: 1,
                            alignItems: 'center',
                            justifyContent: 'center',
                            flexDirection: 'row',
                            height: 52,
                            borderWidth: 1,
                            borderColor: COLORS.GRAY,
                            marginRight: 4,
                            borderRadius: 10
                        }}
                    >
                        <Image
                            source={require("../../assets/Images/facebook.png")}
                            style={{
                                height: 36,
                                width: 36,
                                marginRight: 8
                            }}
                            resizeMode='contain'
                        />
    
                        <Text>Facebook</Text>
                    </TouchableOpacity>
    
                    <TouchableOpacity
                        onPress={() => console.log("Pressed")}
                        style={{
                            flex: 1,
                            alignItems: 'center',
                            justifyContent: 'center',
                            flexDirection: 'row',
                            height: 52,
                            borderWidth: 1,
                            borderColor: COLORS.GRAY,
                            marginRight: 4,
                            borderRadius: 10
                        }}
                    >
                        <Image
                            source={require("../../assets/Images/google.png")}
                            style={{
                                height: 36,
                                width: 36,
                                marginRight: 8
                            }}
                            resizeMode='contain'
                        />
    
                        <Text>Google</Text>
                    </TouchableOpacity>
                </View>
    
                <View style={{
                    flexDirection: "row",
                    justifyContent: "center",
                    marginVertical: 22
                }}>
                    <Text style={{ fontSize: 16, color: COLORS.GRAY }}>Already have an account ? </Text>
                    <TouchableOpacity
                        onPress={() => navigation.navigate("SignIn")}
                    >
                        <Text style={{
                            fontSize: 16,
                            color: COLORS.PRIMARY,
                            fontWeight: "bold",
                            marginLeft: 6
                        }}>Login</Text>
                    </TouchableOpacity>
                </View>
            </View>
        </ScrollView>
    );
};

const styles = StyleSheet.create({
    container: {
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
        marginTop: 16,
    },
    buttonText: {
        color: '#fff',
        fontSize: 16,
    },
    checkboxContainer: {
        flexDirection: 'row',
        alignItems: 'center',
        marginBottom: 16,
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
