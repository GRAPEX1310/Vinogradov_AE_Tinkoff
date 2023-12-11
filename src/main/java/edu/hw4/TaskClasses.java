package edu.hw4;

public class TaskClasses {

    public record Animal(
            String name,
            Type type,
            Sex sex,
            int age,
            int height,
            int weight,
            boolean bites
    ) {
        private static final int SPIDERS_PAWS = 8;
        private static final int DEFAULT_ANIMALS_PAWS = 4;
        private static final int DEFAULT_BIRDS_PAWS = 2;

        enum Type {
            CAT, DOG, BIRD, FISH, SPIDER
        }

        enum Sex {
            M, F
        }

        public int paws() {
            return switch (type) {
                case CAT, DOG -> DEFAULT_ANIMALS_PAWS;
                case BIRD -> DEFAULT_BIRDS_PAWS;
                case FISH -> 0;
                case SPIDER -> SPIDERS_PAWS;
            };
        }
    }
}