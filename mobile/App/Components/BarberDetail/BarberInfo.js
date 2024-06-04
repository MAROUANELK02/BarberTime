import React, { useState, useEffect } from 'react';
import { View, Text, FlatList, TouchableOpacity, TextInput, Modal, Alert } from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import Colors from '../../../assets/Shared/Colors';
import { ActionButton } from './ActionButton';
import { SubHeading } from '../Home/SubHeading';
import { API_BASE_URL } from '../../Config/apiConfig';
import AsyncStorage from '@react-native-async-storage/async-storage';

export function BarberInfo({ barber, customer }) {
  const [comments, setComments] = useState([]);
  const [modalVisible, setModalVisible] = useState(false);
  const [rating, setRating] = useState(0);
  const [commentText, setCommentText] = useState('');

  useEffect(() => {
    const fetchComments = async () => {
      const token = await AsyncStorage.getItem('jwtToken');
      if (!token) {
        console.error('Token is not available');
        return;
      }

      fetch(`${API_BASE_URL}/api/customer/reviews/${barber.idBarberShop}`, {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      })
        .then(response => response.json())
        .then(data => {
          if (Array.isArray(data)) {
            setComments(data);
          } else {
            console.error('Expected an array of comments');
          }
        })
        .catch(error => console.error('Error fetching comments:', error));
    };

    fetchComments();
  }, [barber.idBarberShop]);

  const renderRatingStars = (rating) => {
    const starArray = [];
    for (let i = 1; i <= 5; i++) {
      starArray.push(
        <TouchableOpacity key={i} onPress={() => setRating(i)}>
          <Ionicons
            name={i <= rating ? 'star' : 'star-outline'}
            size={20}
            color={Colors.PRIMARY}
            style={{ marginRight: 5 }}
          />
        </TouchableOpacity>
      );
    }
    return starArray;
  };

  const addComment = async () => {
    const token = await AsyncStorage.getItem('jwtToken');
    if (!token) {
      console.error('Token is not available');
      return;
    }

    const customerId = customer.idUser;
    const barberShopId = barber.idBarberShop;
    const reviewReqDTO = {
      comment: commentText,
      rating: rating,
    };

    fetch(`${API_BASE_URL}/api/customer/review?customerId=${customerId}&barberShopId=${barberShopId}`, {
      method: 'POST',
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(reviewReqDTO),
    })
      .then(response => {
        if (!response.ok) {
          throw new Error('Failed to add comment');
        }
        return response.json();
      })
      .then(data => {
        setComments([...comments, data]);
        setRating(0);
        setCommentText('');
        setModalVisible(false);
        Alert.alert('Success', 'Your comment has been successfully added.');
      })
      .catch(error => {
        console.error('Error adding comment:', error);
        Alert.alert('Error', 'Failed to add comment. Please try again later.');
      });
  };

  return barber && (
    <View>
      <Text style={{ fontSize: 23 }}>{barber.name}</Text>
      <FlatList
        data={barber.services}
        horizontal={true}
        renderItem={({ item }) => (
          <Text style={{ marginRight: 10, color: Colors.GRAY }}>
            {item.serviceName}
          </Text>
        )}
        keyExtractor={(item) => item.idService.toString()}
      />

      <View
        style={{
          borderBottomWidth: 1,
          borderColor: Colors.LIGHT_GRAY,
          margin: 5,
          marginBottom: 16,
        }}
      ></View>

      <View style={{ flexDirection: 'row', alignItems: 'center' }}>
        <Ionicons name="location" size={22} color={Colors.PRIMARY} />
        <Text style={{ fontSize: 16, color: Colors.GRAY }}>{barber.address}, {barber.neighborhood}</Text>
      </View>

      <View style={{ flexDirection: 'row', alignItems: 'center', marginTop: 6 }}>
        <Ionicons name="time" size={22} color={Colors.PRIMARY} />
        <Text style={{ fontSize: 16, color: Colors.GRAY }}> {barber.startTime} - {barber.endTime} | day Off: {barber.dayOff}</Text>
      </View>

      <View
        style={{
          borderBottomWidth: 1,
          borderColor: Colors.LIGHT_GRAY,
          margin: 5,
          marginBottom: 16,
          marginTop: 10
        }}
      ></View>

      <ActionButton barber={barber} />

      <View
        style={{
          borderBottomWidth: 1,
          borderColor: Colors.LIGHT_GRAY,
          marginTop: 15,
          margin: 5,
          marginBottom: 16,
        }}
      ></View>

      <SubHeading subHeadingTitle={'About'} />
      <Text>Le {barber.name} est une référence incontournable pour les amateurs de soins capillaires masculins. Avec une équipe de coiffeurs talentueux et une ambiance conviviale, il offre une gamme complète de services, allant des coupes de cheveux classiques aux soins de la barbe, dans un cadre moderne et élégant.</Text>

      <View
        style={{
          borderBottomWidth: 1,
          borderColor: Colors.LIGHT_GRAY,
          marginTop: 15,
          margin: 5,
          marginBottom: 16,
        }}
      ></View>

      <SubHeading subHeadingTitle={'Comments'} />

      <TouchableOpacity
        onPress={() => setModalVisible(true)}
        style={{ flexDirection: 'row', alignItems: 'center', marginTop: 10 }}
      >
        <Ionicons name="add-circle-outline" size={24} color={Colors.PRIMARY} />
        <Text style={{ fontSize: 16, color: Colors.PRIMARY, marginLeft: 5 }}>Add a comment</Text>
      </TouchableOpacity>

      {/* Liste des commentaires */}
      {comments.length > 0 ? (
        comments.map((item, index) => (
          <View key={index} style={{ marginTop: 10 }}>
            <View style={{ flexDirection: 'row', alignItems: 'center' }}>
              <Text style={{ fontWeight: 'bold' }}>{item.customerDTO.firstName} {item.customerDTO.lastName}</Text>
              <View style={{ flexDirection: 'row', marginLeft: 10 }}>
                {renderRatingStars(item.rating)}
              </View>
            </View>
            <Text>{item.comment}</Text>
            {/* Ajout de la ligne entre les commentaires */}
            {index !== comments.length - 1 && <View style={{ borderBottomWidth: 1, borderColor: Colors.LIGHT_GRAY, marginTop: 10 }}></View>}
          </View>
        ))
      ) : (
        <Text style={{ marginTop: 10, color: Colors.GRAY }}>No comments yet.</Text>
      )}

      {/* Modal pour ajouter un commentaire */}
      <Modal
        animationType="slide"
        transparent={true}
        visible={modalVisible}
        onRequestClose={() => setModalVisible(false)}
      >
        <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
          <View style={{ backgroundColor: 'white', padding: 20, borderRadius: 10, width: '80%' }}>
            <Text style={{ fontSize: 18, marginBottom: 10 }}>Add a comment</Text>
            <View style={{ flexDirection: 'row', alignItems: 'center', marginBottom: 10 }}>
              <Text style={{ marginRight: 10 }}>Rating:</Text>
              <View style={{ flexDirection: 'row' }}>
                {renderRatingStars(rating)}
              </View>
            </View>
            <TextInput
              placeholder="Write your comment here..."
              style={{ 
                borderWidth: 1, 
                borderColor: Colors.LIGHT_GRAY, 
                padding: 5, 
                marginBottom: 10,
                borderRadius: 5 
              }}
              multiline={true}
              value={commentText}
              onChangeText={(text) => setCommentText(text)}
            />
            <View style={{ flexDirection: 'row', justifyContent: 'space-between' }}>
              <TouchableOpacity onPress={() => setModalVisible(false)}>
                <Text style={{ color: Colors.PRIMARY }}>Cancel</Text>
              </TouchableOpacity>
              <TouchableOpacity onPress={addComment}>
                <Text style={{ color: Colors.PRIMARY }}>Save</Text>
              </TouchableOpacity>
            </View>
          </View>
        </View>
      </Modal>
    </View>
  );
}
