package alexsoroka.bots;

/**
 * Bidder with bids that depends on previous opponent bid.
 */
public class OpponentPlusOneBidder extends AbstractBidder {

  /**
   * Last other's bid. 0 by default.
   */
  private int lastOpponentBid;

  @Override
  protected void afterInit(int quantity, int cash) {
    this.lastOpponentBid = 0;
  }

  /**
   * @return the next bid that it is the same as previous opponent's bid plus one
   */
  @Override
  public int placeBid() {
    int nextValue = lastOpponentBid + 1;
    return zeroIfGreaterThanCash(nextValue, ownCash);
  }

  @Override
  protected void afterBids(int own, int other) {
    this.lastOpponentBid = other;
  }
}
