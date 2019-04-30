package com.troystopera.jkode.evaluations

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.exceptions.runtime.ArrayIndexException
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.Array2DVar
import com.troystopera.jkode.vars.IntVar
import com.troystopera.jkode.vars.JVar
import com.troystopera.jkode.vars.VarType

class Array2DAccess<T : JVar<*>>(
        varType: VarType<T>,
        val array: Evaluation<Array2DVar<T>>,
        val rowIndex: Evaluation<IntVar>,
        val colIndex: Evaluation<IntVar>
) : Evaluation<T>(varType) {

    override fun onExecute(scope: Scope, executor: Executor, output: MutableOutput?): T {
        val arrVar = array.execute(scope, executor, output)
        val rowIndex = rowIndex.execute(scope, executor, output).value
        val colIndex = colIndex.execute(scope, executor, output).value
        return if (rowIndex < arrVar.value.size) arrVar.value[rowIndex][colIndex] ?: varType.NULL
        else throw ArrayIndexException(arrVar.value.size, rowIndex)
    }

}