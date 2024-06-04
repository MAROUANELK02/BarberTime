import React, { useState, useEffect } from 'react';
import { View, Text, StyleSheet, Image, TouchableOpacity, TextInput, ScrollView, Alert, Modal } from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import * as ImagePicker from 'expo-image-picker';
import { API_BASE_URL } from '../Config/apiConfig';
import COLORS from '../../assets/Shared/Colors';
import { useNavigation } from '@react-navigation/native';
import AsyncStorage from '@react-native-async-storage/async-storage';

export function Profile({ customer }) {
  const [isEditing, setIsEditing] = useState(false);
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [username, setUsername] = useState('');
  const [phone, setPhone] = useState('');
  const [verificationCode, setVerificationCode] = useState('');
  const [code, setCode] = useState('');
  const [isChangingPassword, setIsChangingPassword] = useState(false);
  const [password, setPassword] = useState('');
  const [showPassword, setShowPassword] = useState(false);
  const [confirmPassword, setConfirmPassword] = useState('');
  const [showConfirmPassword, setShowConfirmPassword] = useState(false);
  const [profileImage, setProfileImage] = useState(require('../../assets/Images/pic.png'));
  const [showModal, setShowModal] = useState(false);
  const [verificationCodeSent, setVerificationCodeSent] = useState(false);
  const [isVerificationCodeCorrect, setIsVerificationCodeCorrect] = useState(false);

  const navigation = useNavigation();

  useEffect(() => {
    if (customer) {
      setFirstName(customer.firstName);
      setLastName(customer.lastName);
      setUsername(customer.username);
      setEmail(customer.email);
      setPhone(customer.telNumber);
    }
  }, [customer]);

  const handleEditProfile = () => {
    setIsEditing(!isEditing);
  };

  const saveProfileChanges = async () => {
    try {
      const token = await AsyncStorage.getItem('jwtToken');
      const response = await fetch(`${API_BASE_URL}/api/customer/${customer.idUser}`, {
        method: 'PATCH',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`,
        },
        body: JSON.stringify({
          email,
          username,
          telNumber: phone,
          firstName,
          lastName,
        }),
      });
      if (response.ok) {
        setIsEditing(false);
        Alert.alert('Success', 'Profile changes saved successfully');
      } else {
        console.error('Failed to update customer profile');
      }
    } catch (error) {
      console.error('Error updating customer profile:', error);
    }
  };

  const deleteCustomer = async () => {
    try {
      const token = await AsyncStorage.getItem('jwtToken');
      const response = await fetch(`${API_BASE_URL}/api/customer/${customer.idUser}`, {
        method: 'DELETE',
        headers: {
          'Authorization': `Bearer ${token}`,
        },
      });

      if (response.ok) {
        Alert.alert('Success', 'Customer deleted successfully');
        await AsyncStorage.removeItem('jwtToken');
        navigation.navigate('SignIn');
      } else {
        console.error('Failed to delete customer');
      }
    } catch (error) {
      console.error('Error deleting customer:', error);
    }
  };

  const confirmDeleteCustomer = () => {
    Alert.alert(
      'Confirm Deletion',
      'Are you sure you want to delete your account?',
      [
        { text: 'Cancel', style: 'cancel' },
        { text: 'Delete', onPress: deleteCustomer, style: 'destructive' },
      ],
      { cancelable: true }
    );
  };

  const handleLogout = async () => {
    await AsyncStorage.removeItem('jwtToken');
    navigation.navigate('SignIn');
  };

  const handleChooseImage = async () => {
    let result = await ImagePicker.launchImageLibraryAsync({
      mediaTypes: ImagePicker.MediaTypeOptions.Images,
      allowsEditing: true,
      aspect: [1, 1],
      quality: 1,
    });
    if (!result.cancelled) {
      setProfileImage({ uri: result.uri });
    }
  };

  const toggleChangePassword = () => {
    setIsChangingPassword(!isChangingPassword);
  };

  const openVerificationModal = async () => {
    try {
      const response = await fetch(`${API_BASE_URL}/api/changePassword`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          email,
        }),
      });
      if (response.ok) {
        const data = await response.json()
        setVerificationCode(data);
        setVerificationCodeSent(true);
        setShowModal(true);
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

  return (
    <ScrollView contentContainerStyle={styles.container}>
      <View style={styles.header}>
        <TouchableOpacity onPress={handleChooseImage}>
          <Text style={styles.chooseImageText}>Choose a photo</Text>
        </TouchableOpacity>
        <Image source={profileImage} style={styles.profileImage} />
        {!isEditing ? (
          <>
            <Text style={styles.username}>{firstName} {lastName}</Text>
            <Text style={styles.bio}>@{username}</Text>
          </>
        ) : (
          <>
            <TextInput style={styles.input} value={username} placeholder="Username" editable={false} />
            <TextInput style={styles.input} value={email} placeholder="Email" editable={false} />
            <TextInput style={styles.input} value={firstName} onChangeText={text => setFirstName(text)} placeholder="First Name" />
            <TextInput style={styles.input} value={lastName} onChangeText={text => setLastName(text)} placeholder="Last Name" />
            <TextInput style={styles.input} value={phone} onChangeText={text => setPhone(text)} placeholder="Phone" keyboardType="phone-pad" />
          </>
        )}
        <View style={styles.iconContainer}>
          {!isEditing ? (
            <>
              <TouchableOpacity onPress={handleEditProfile} style={styles.iconButton}>
                <Ionicons name="pencil" size={24} color="#007BFF" />
              </TouchableOpacity>
              <TouchableOpacity onPress={confirmDeleteCustomer} style={styles.iconButton}>
                <Ionicons name="trash" size={24} color="#FF0000" />
              </TouchableOpacity>
              <TouchableOpacity onPress={handleLogout} style={styles.iconButton}>
                <Ionicons name="log-out" size={24} color="#007BFF" />
              </TouchableOpacity>
              <TouchableOpacity onPress={openVerificationModal} style={styles.iconButton}>
                <Ionicons name="key" size={24} color="#007BFF" />
              </TouchableOpacity>
            </>
          ) : (
            <>
              <TouchableOpacity onPress={handleEditProfile} style={styles.iconButton}>
                <Ionicons name="close" size={24} color="#FF0000" />
              </TouchableOpacity>
              <TouchableOpacity onPress={saveProfileChanges} style={styles.iconButton}>
                <Ionicons name="checkmark" size={24} color="#007BFF" />
              </TouchableOpacity>
            </>
          )}
        </View>
        
      </View>
      <View style={styles.footer}>
        <Text style={styles.sectionTitle}>Profile Details</Text>
        <View style={styles.detailItem}>
          <Ionicons name="mail" size={24} color="#333333" />
          <Text style={styles.detailText}>{email}</Text>
        </View>
        <View style={styles.detailItem}>
          <Ionicons name="call" size={24} color="#333333" />
          <Text style={styles.detailText}>{phone}</Text>
        </View>
      </View>
      {/* Modal */}
      <Modal
        animationType="slide"
        transparent={true}
        visible={showModal}
        onRequestClose={() => setShowModal(false)}
      >
        <View style={styles.modalContainer}>
          <View style={styles.modalContent}>
            {!isVerificationCodeCorrect ? (
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
                <View style={styles.iconContainer}>
              <TouchableOpacity onPress={handleChangePassword} style={styles.iconButton}>
                <Ionicons name="checkmark" size={24} color="#007BFF" />
              </TouchableOpacity>
              <TouchableOpacity onPress={toggleChangePassword} style={styles.iconButton}>
                <Ionicons name="close" size={24} color="#FF0000" />
              </TouchableOpacity>
            </View>
              </>
            )}
          </View>
        </View>
      </Modal>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    flexGrow: 1,
    backgroundColor: '#FFFFFF',
  },
  header: {
    alignItems: 'center',
    paddingVertical: 20,
    borderBottomWidth: 1,
    borderBottomColor: '#CCCCCC',
  },
  profileImage: {
    width: 150,
    height: 150,
    borderRadius: 75,
    marginBottom: 10,
  },
  username: {
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom: 5,
  },
  bio: {
    fontSize: 16,
    marginBottom: 10,
    textAlign: 'center',
    paddingHorizontal: 20,
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
  iconContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    width: '80%',
    marginTop: 20,
  },
  iconButton: {
    paddingHorizontal: 20,
  },
  footer: {
    paddingHorizontal: 20,
    paddingTop: 20,
  },
  sectionTitle: {
    fontSize: 20,
    fontWeight: 'bold',
    marginBottom: 10,
  },
  detailItem: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 10,
  },
  detailText: {
    marginLeft: 10,
    fontSize: 16,
  },
  chooseImageText: {
    marginBottom: 10,
    color: 'blue',
    fontWeight: 'bold',
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
  // Styles pour le modal
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
});

export default Profile;
