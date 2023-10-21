import re
from collections import defaultdict

def find_repeating_sequences(text, min_length=3):
    # Create a dictionary to store the positions of repeating sequences
    sequence_positions = defaultdict(list)

    for i in range(len(text) - min_length + 1):
        sequence = text[i:i + min_length]
        if sequence in sequence_positions:
            sequence_positions[sequence].append(i)
        else:
            sequence_positions[sequence] = [i]

    # Filter sequences that appear more than once
    repeating_sequences = {sequence: positions for sequence, positions in sequence_positions.items() if len(positions) > 1}

    return repeating_sequences

def find_key_length(text):
    # Find repeating sequences in the text
    repeating_sequences = find_repeating_sequences(text)

    # Calculate the distances between repeating sequences
    sequence_distances = defaultdict(list)
    for sequence, positions in repeating_sequences.items():
        for i in range(len(positions) - 1):
            distance = positions[i + 1] - positions[i]
            sequence_distances[sequence].append(distance)

    # Find the greatest common divisor (GCD) of the distances for each repeating sequence
    possible_key_lengths = []
    for sequence, distances in sequence_distances.items():
        gcd = distances[0]
        for distance in distances[1:]:
            gcd = find_gcd(gcd, distance)
        possible_key_lengths.extend([gcd])

    return possible_key_lengths

def find_gcd(a, b):
    while b:
        a, b = b, a % b
    return a

if __name__ == "__main__":
    encrypted_text = input("Enter the encrypted text: ").upper()

    possible_key_lengths = find_key_length(encrypted_text)

    if not possible_key_lengths:
        print("No repeating sequences found. Unable to determine the key length.")
    else:
        print("Possible key lengths:", possible_key_lengths)