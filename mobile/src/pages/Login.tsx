import React, {useState, useEffect} from 'react';
import { View, Text, TouchableOpacity, Image, TextInput } from 'react-native';
import { theme, text } from '../styles'
import eyesOpened from '../assets/eyes-opened.png'
import eyesClosed from '../assets/eyes-closed.png'
import arrow from '../assets/arrow.png'
import { isAuthenticated, login } from '../services/auth';

const Login: React.FC = () => {
    const [hidePassword, setHidePassword] = useState(true);
    const [userInfo, setUserInfo] = useState({ username: "", password: "" });

    useEffect(() => {
        isAuthenticated();
    }, []);
    
    async function handleLogin() {
        const data = await login(userInfo);
        console.warn(data);
    } 

    return (
        <View style={theme.container}>
            <View style={theme.loginCard}>
                <Text style={text.loginTitle}>Login</Text>
                <View style={theme.form}>
                    <TextInput 
                        placeholder="Email" 
                        autoCapitalize="none" 
                        keyboardType="email-address"
                        style={theme.textInput}
                        value={userInfo.username}
                        onChangeText = {(e) => {
                            const newUserInfo = { ...userInfo };
                            newUserInfo.username = e;
                            setUserInfo(newUserInfo);
                        }}
                    />
                    <View style={theme.passwordGroup}>
                        <TextInput 
                            placeholder="Senha" 
                            autoCapitalize="none" 
                            style={theme.textInput}
                            secureTextEntry={hidePassword}
                            value={userInfo.password}
                            onChangeText = {(e) => {
                                const newUserInfo = { ...userInfo };
                                newUserInfo.password = e;
                                setUserInfo(newUserInfo);
                            }}
                        />
                        <TouchableOpacity 
                            style={theme.toggle}
                            onPress={() => setHidePassword(!hidePassword)}
                        >
                            <Image 
                                source={hidePassword ? eyesOpened : eyesClosed} 
                                style={theme.eyes}
                            />
                        </TouchableOpacity>
                    </View>

                    <TouchableOpacity 
                        style={theme.primaryButton} 
                        activeOpacity={0.8} 
                        onPress={() => handleLogin()}
                    >
                        <View style={theme.buttonTextContainer}>
                            <Text style={text.primaryText}>Fazer Login</Text>
                        </View>
                        <View style={theme.arrowContainer}>
                            <Image source={arrow}/>
                        </View>
                    </TouchableOpacity>
                </View>
            </View>
        </View>
    );
};

export default Login;