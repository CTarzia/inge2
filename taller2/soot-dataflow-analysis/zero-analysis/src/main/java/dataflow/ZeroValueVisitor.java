package dataflow;

import java.util.Map;

import dataflow.abs.ZeroAbstractValue;
import dataflow.abs.ZeroAbstractSet;
import dataflow.utils.AbstractValueVisitor;
import dataflow.utils.ValueVisitor;
import soot.jimple.ParameterRef;
import soot.Local;

public class ZeroValueVisitor extends AbstractValueVisitor<ZeroAbstractValue> {

  private final ZeroAbstractSet set;
  private ZeroAbstractValue resolvedValue;
  private Boolean possibleDivisionByZero;

  public ZeroValueVisitor(ZeroAbstractSet set) {
    this.set = set;
    this.possibleDivisionByZero = false;
  }

  @Override
  protected void visitParameterRef(ParameterRef parameter) {
    resolvedValue = ZeroAbstractValue.MAYBE_ZERO;
  }

  @Override
  public void visitLocal(Local variable) {
    String name = variable.getName();
    if (set.hasValue(name)){
      resolvedValue = set.getValue(name);
    } else{
      resolvedValue = ZeroAbstractValue.ZERO;
    }
  }

  @Override
  public void visitDivExpression(ZeroAbstractValue leftOperand, ZeroAbstractValue rightOperand) {
    // Tener en cuenta que this.possibleDivisionByZero indica que hay una posible division.
    if(rightOperand == ZeroAbstractValue.ZERO || rightOperand == ZeroAbstractValue.MAYBE_ZERO){
      this.possibleDivisionByZero = true;
    }
    resolvedValue = leftOperand.divideBy(rightOperand);
  }
  

  @Override
  public void visitMulExpression(ZeroAbstractValue leftOperand, ZeroAbstractValue rightOperand) {
    resolvedValue = leftOperand.multiplyBy(rightOperand);
  }

  @Override
  public void visitSubExpression(ZeroAbstractValue leftOperand, ZeroAbstractValue rightOperand) {
    resolvedValue = leftOperand.substract(rightOperand);
  }

  @Override
  public void visitAddExpression(ZeroAbstractValue leftOperand, ZeroAbstractValue rightOperand) {
    resolvedValue = leftOperand.add(rightOperand);
  }

  @Override
  public void visitIntegerConstant(int value) {
    // Tener en cuenta que this.resolvedValue contiene el valor abstracto que se quiere devolver.
    resolvedValue = value == 0 ? ZeroAbstractValue.ZERO : ZeroAbstractValue.NOT_ZERO;
  }

  @Override
  public ZeroAbstractValue done() {
    return resolvedValue;
  }

  @Override
  public ValueVisitor cloneVisitor() {
    return new ZeroValueVisitor(set);
  }

  public Boolean getPossibleDivisionByZero() {
    return possibleDivisionByZero;
  }
}
