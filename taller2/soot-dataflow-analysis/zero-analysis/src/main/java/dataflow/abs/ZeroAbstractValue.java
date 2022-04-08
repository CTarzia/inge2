package dataflow.abs;

public enum ZeroAbstractValue {

  BOTTOM("bottom"), NOT_ZERO("not-zero"), ZERO("zero"), MAYBE_ZERO("maybe-zero");

  private String name;

  @Override
  public String toString() {
    return this.name;
  }

  ZeroAbstractValue(String name) {
    this.name = name;
  }

  public ZeroAbstractValue add(ZeroAbstractValue another) {
    return addOrSub(another);
  }



  public ZeroAbstractValue divideBy(ZeroAbstractValue another) {
    if( another == ZERO || another == BOTTOM || this == BOTTOM){
      return BOTTOM;
    }
    else{
      return this;
    }
  }

  public ZeroAbstractValue multiplyBy(ZeroAbstractValue another) {
    if(another == BOTTOM || this == BOTTOM){
      return BOTTOM;
    }
    else if(another == ZERO || this == ZERO){
      return ZERO;
    }
    else if( another == NOT_ZERO || this == NOT_ZERO){
      return NOT_ZERO;
    }
    else{
      return MAYBE_ZERO;
    }
  }

  public ZeroAbstractValue substract(ZeroAbstractValue another) {
    return addOrSub(another);
  }

  public ZeroAbstractValue merge(ZeroAbstractValue another) {
    if(this == BOTTOM || another == BOTTOM){
      return BOTTOM;
    }
    else if( another == MAYBE_ZERO || this == MAYBE_ZERO){
      return MAYBE_ZERO;
    }
    else if( another == this){
      return this;
    }
    else {
      return MAYBE_ZERO;
    }
  }

  private ZeroAbstractValue addOrSub(ZeroAbstractValue another) {
    if(another == ZeroAbstractValue.BOTTOM || this == ZeroAbstractValue.BOTTOM){
      return ZeroAbstractValue.BOTTOM;
    }
    else if( another == ZeroAbstractValue.ZERO) {
      return this;
    }
    else if( this == ZeroAbstractValue.ZERO){
      return another;
    }
    else{
      return ZeroAbstractValue.MAYBE_ZERO;
    }
  }
}
