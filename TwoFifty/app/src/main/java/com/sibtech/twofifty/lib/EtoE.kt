package com.sibtech.twofifty.lib

import android.os.Build
import androidx.annotation.RequiresApi
import java.security.Key
import java.security.KeyFactory
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.RSAKeyGenParameterSpec
import java.security.spec.X509EncodedKeySpec
import java.util.Base64
import javax.crypto.Cipher


fun generateKeyPair(): KeyPair {
    val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
    keyPairGenerator.initialize(2048)
    return keyPairGenerator.generateKeyPair()
}

fun encryptRSA(data: ByteArray, publicKey: Key): ByteArray {
    val cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding")
    cipher.init(Cipher.ENCRYPT_MODE, publicKey)
    return cipher.doFinal(data)
}

fun encryptMessage(message: String, publicKey: String): String {
    val resByteArr = encryptRSA(message.toByteArray(), publicKeyFromString(publicKey))
    return resByteArr.toString()
}

fun privateKeyToString(privateKey: PrivateKey): String {
    val keyBytes = privateKey.encoded
    val base64 = Base64.getEncoder().encodeToString(keyBytes)
    return base64
}

fun privateKeyFromString(privateKeyString: String): PrivateKey {
    val keyBytes = Base64.getDecoder().decode(privateKeyString)
    val spec = PKCS8EncodedKeySpec(keyBytes)
    val keyFactory = KeyFactory.getInstance("RSA")
    return keyFactory.generatePrivate(spec)
}

fun publicKeyToString(publicKey: PublicKey): String {
    val keyBytes = publicKey.encoded
    val base64 = Base64.getEncoder().encodeToString(keyBytes)
    return base64
}

fun publicKeyFromString(publicKeyString: String): PublicKey {
    val keyBytes = Base64.getDecoder().decode(publicKeyString)
    val spec = X509EncodedKeySpec(keyBytes)
    val keyFactory = KeyFactory.getInstance("RSA")
    return keyFactory.generatePublic(spec)
}

fun decryptRSA(data: ByteArray, privateKey: Key): ByteArray {
    val cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding")
    cipher.init(Cipher.DECRYPT_MODE, privateKey)
    return cipher.doFinal(data)
}

fun decryptMessage(message: String, privateKey: String):String{
    val resByteArr = encryptRSA(message.toByteArray(), privateKeyFromString(privateKey))
    return resByteArr.toString()
}