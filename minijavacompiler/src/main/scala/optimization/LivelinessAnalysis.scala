package optimization

import soot.Local
import soot.toolkits.graph.DirectedGraph
import soot.toolkits.scalar.{ArraySparseSet, BackwardFlowAnalysis}

class LivelinessAnalysis(graph: DirectedGraph[soot.Unit]) extends BackwardFlowAnalysis[soot.Unit, soot.toolkits.scalar.ArraySparseSet[soot.Local]](graph){
  override def flowThrough(in: ArraySparseSet[Local], d: soot.Unit, out: ArraySparseSet[Local]): Unit = ???

  override def newInitialFlow(): ArraySparseSet[Local] = ???

  override def merge(in1: ArraySparseSet[Local], in2: ArraySparseSet[Local], out: ArraySparseSet[Local]): Unit = ???

  override def copy(source: ArraySparseSet[Local], dest: ArraySparseSet[Local]): Unit = ???
}
