import React, { useState } from 'react';
import { View, Text, TextInput } from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import Colors from '../../../assets/Shared/Colors';

export function SearchBar({ setSearchText }) {
  const [searchInput, setSearchInput] = useState('');

  const handleInputChange = (value) => {
    setSearchInput(value);
  };

  const handleSearch = () => {
    setSearchText(searchInput);
  };

  return (
    <View style={{ marginTop: 15 }}>
      <View
        style={{
          display: 'flex',
          flexDirection: 'row',
          gap: 5,
          alignItems: 'center',
          borderWidth: 0.6,
          borderColor: Colors.GRAY,
          padding: 8,
          borderRadius: 8,
        }}
      >
        <Ionicons name="search-outline" size={24} color={Colors.PRIMARY} />
        <TextInput
          placeholder="Search"
          value={searchInput}
          onChangeText={handleInputChange}
          onSubmitEditing={handleSearch}
          style={{ width: '100%' }}
        />
      </View>
    </View>
  );
}
