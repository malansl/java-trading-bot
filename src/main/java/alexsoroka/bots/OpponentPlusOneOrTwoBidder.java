package alexsoroka.bots;

import alexsoroka.util.Assert;

import java.util.Random;

/**
 * Bidder with bids that depend on previous opponent's bid.
 */
public class OpponentPlusOneOrTwoBidder implements Bidder {

  /**
   * Current value of bidder money. 0 by default.
   */
  private int cash;

  /**
   * Last other's bid. 0 by default.
   */
  private int lastOpponentBid;

  /**
   * @throws IllegalArgumentException if quantity or cash are negative numbers
   */
  @Override
  public void init(int quantity, int cash) {
    Assert.isTrue(quantity >= 0, "Quantity must be a positive number");
    Assert.isTrue(cash >= 0, "Cash must be a positive number");

    this.cash = cash;
    this.lastOpponentBid = 0;
  }

  /**
   * @return the next bid that it is the same as previous winner's bid plus one
   */
  @Override
  public int placeBid() {
    int nextValue = lastOpponentBid + (new Random().nextBoolean() ? 1 : 2);
    return nextValue <= cash ? nextValue : 0;
  }

  /**
   * @throws IllegalArgumentException if own or other are negative numbers
   */
  @Override
  public void bids(int own, int other) {
    Assert.isTrue(own >= 0, "Own bid must be a positive number");
    Assert.isTrue(other >= 0, "Other bid must be a positive number");

    this.cash -= own;
    this.lastOpponentBid = other;
  }
}
