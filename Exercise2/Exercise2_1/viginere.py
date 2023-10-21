#declare function to encrypt inputed text
def encrypt(plain_text, key):
    encrypted_text = ""
    key = key.upper()
    key_length = len(key)
    
    for i in range(len(plain_text)):
        char = plain_text[i]
        if char.isalpha():
            key_char = key[i % key_length]
            shift = ord(key_char) - ord('A')
            
            if char.islower():
                encrypted_char = chr(((ord(char) - ord('a') + shift) % 26) + ord('a'))
            else:
                encrypted_char = chr(((ord(char) - ord('A') + shift) % 26) + ord('A'))
            
            encrypted_text += encrypted_char
        else:
            encrypted_text += char
    
    return encrypted_text

#declare function to decrypt inputed text
def decrypt(encrypted_text, key):
    decrypted_text = ""
    key = key.upper()
    key_length = len(key)
    
    for i in range(len(encrypted_text)):
        char = encrypted_text[i]
        if char.isalpha():
            key_char = key[i % key_length]
            shift = ord(key_char) - ord('A')
            
            if char.islower():
                decrypted_char = chr(((ord(char) - ord('a') - shift) % 26) + ord('a'))
            else:
                decrypted_char = chr(((ord(char) - ord('A') - shift) % 26) + ord('A'))
            
            decrypted_text += decrypted_char
        else:
            decrypted_text += char
    
    return decrypted_text


#main function to output
if __name__ == "__main__":
    choice = input("Enter 'E' for encryption or 'D' for decryption: ")
    if choice.upper() == 'E':
        plain_text = input("Enter the text: ")
        key = input("Enter the encryption key: ")
        encrypted_text = encrypt(plain_text, key)
        print(f"Encrypted text: {encrypted_text}")
    elif choice.upper() == 'D':
        encrypted_text = input("Enter the encrypted text: ")
        key = input("Enter the decryption key: ")
        decrypted_text = decrypt(encrypted_text, key)
        print(f"Decrypted text: {decrypted_text}")
    else:
        print("Invalid choice. Please enter 'E' for encryption or 'D' for decryption.")