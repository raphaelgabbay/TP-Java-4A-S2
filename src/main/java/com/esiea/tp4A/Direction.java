package com.esiea.tp4A;

public enum Direction implements PolarDegreesRotation {
    N{
        @Override
        public Direction rotateLeft() {
            return W;
        }

        @Override
        public Direction rotateRight() {
            return E;
        }
    },
    S {
        @Override
        public Direction rotateLeft() {
            return E;
        }

        @Override
        public Direction rotateRight() {
            return W;
        }
    },
    E {
        @Override
        public Direction rotateLeft() {
            return N;
        }

        @Override
        public Direction rotateRight() {
            return S;
        }
    },
    W {
        @Override
        public Direction rotateLeft() {
            return S;
        }

        @Override
        public Direction rotateRight() {
            return N;
        }
    }
}
