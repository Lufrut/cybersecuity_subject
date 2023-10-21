import collections
import math

# Function to calculate the key length using the Kasiski examination
def kasiski_examination(ciphertext, max_key_length=20):
    distances = {}
    for length in range(3, max_key_length + 1):
        for i in range(len(ciphertext) - length + 1):
            substr = ciphertext[i:i + length]
            if substr in distances:
                distances[substr].append(i)
            else:
                distances[substr] = [i]

    repeating_distances = {}
    for substr, pos_list in distances.items():
        if len(pos_list) > 1:
            repeating_distances[substr] = [
                pos_list[i + 1] - pos_list[i] for i in range(len(pos_list) - 1)
            ]        

    probable_key_lengths = []
    for substr, distances in repeating_distances.items():
        gcd = distances[0]
        for dist in distances:
            gcd = math.gcd(gcd, dist)
        probable_key_lengths.append(gcd)

    return collections.Counter(probable_key_lengths).most_common(1)[0][0]


# Function to perform frequency analysis on a column
def frequency_analysis(column):
    alphabet = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
    frequencies = {}
    for letter in alphabet:
        frequencies[letter] = column.count(letter)

    return max(frequencies, key=frequencies.get)


# Function to decrypt the Vigenere cipher
def vigenere_decrypt(ciphertext, key):
    decrypted_text = ''
    key_length = len(key)
    for i in range(len(ciphertext)):
        shift = ord(key[i % key_length]) - ord('A')
        if ciphertext[i].isalpha():
            if ciphertext[i].islower():
                decrypted_text += chr(((ord(ciphertext[i]) - ord('a') - shift) % 26) + ord('a'))
            else:
                decrypted_text += chr(((ord(ciphertext[i]) - ord('A') - shift) % 26) + ord('A'))
        else:
            decrypted_text += ciphertext[i]

    return decrypted_text


# Input ciphertext
ciphertext = "YOUR_CIPHER_TEXT_HERE"
ciphertext = ciphertext.upper()  # Convert to uppercase for consistency

# Step 1: Calculate key length using Kasiski examination
key_length = kasiski_examination(ciphertext)

# Step 2: Divide the ciphertext into columns
columns = ['' for _ in range(key_length)]
for i in range(len(ciphertext)):
    columns[i % key_length] += ciphertext[i]

# Step 3: Perform frequency analysis on each column to guess the key for that part
guessed_key = ''
for column in columns:
    guessed_key += frequency_analysis(column)

# Step 4: Combine the guessed key parts to get the full key
print("Guessed Key:", guessed_key)

# Step 5: Decrypt the ciphertext using the key
decrypted_text = vigenere_decrypt(ciphertext, guessed_key)
print("Decrypted Text:", decrypted_text)
