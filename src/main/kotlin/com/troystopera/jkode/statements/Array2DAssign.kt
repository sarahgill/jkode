package com.troystopera.jkode.statements

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.Statement
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.Array2DVar
import com.troystopera.jkode.vars.IntVar
import com.troystopera.jkode.vars.JVar

class Array2DAssign<T : JVar<*>>(
        val array: Evaluation<Array2DVar<T>>,
        val rowIndex: Evaluation<IntVar>,
        val colIndex: Evaluation<IntVar>,
        val value: Evaluation<T>
) : Statement() {

    override fun onExecute(scope: Scope, executor: Executor, output: MutableOutput?) = array.execute(scope, executor, output)
            .set(rowIndex.execute(scope, executor, output).value,
                    colIndex.execute(scope, executor, output).value,
                    value.execute(scope, executor, output)
            )
}