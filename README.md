# phylotree

This program was created as the final project for CIT 5940, and is a phylogenetic tree maker whose end goal is the construct a tree of the evolutionary relationships between a host of species based on their DNA sequences.

The input is a folder of FASTA files, each with the DNA sequence of a common protein for all the of the species we are plotting the tree for. We parse each sequence, and then use dynamic programming to calculate the edit distance between each pair of sequences in order to figure out how evolutionarily diverged each pair is. Next, we use an algorithm that we made based on Kruskal's algorithm and Huffman tree construction in order to create the phylogenetic tree. Finally, we have a simple GUI that can display the phylogenetic tree and allow you to see how closely each species is related to all of its peers.

![image](https://github.com/cit5940-23sp/phylotree/assets/131420098/69a00f81-837d-45d1-a9f4-093e2b5c5501)

Team:
Armaan Rathi,
Michael Mason,
Priya D'costa,
Sean Chuang

Method Description: https://docs.google.com/document/d/1ILJZi3arhTQOXOoXex1KGMYUPtoR9tlqSi_7xIoc-LU/edit
