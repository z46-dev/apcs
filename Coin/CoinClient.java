package Coin;

public class CoinClient {
    public static void main(String[] args) {
        Coin coin = new Coin();

        int heads = 0,
            tails = 0;

        for (int i = 0; i < 10; i++) {
            coin.flip();

            if (coin.isHeads()) {
                heads++;
            } else {
                tails++;
            }
        }

        System.out.println("Heads: " + heads);
        System.out.println("Tails: " + tails);

        System.out.println(coin);
    }
}
