package com.troystopera.jkode.evaluations

import com.troystopera.jkode.Evaluation
import com.troystopera.jkode.exec.Executor
import com.troystopera.jkode.exec.MutableOutput
import com.troystopera.jkode.exec.Scope
import com.troystopera.jkode.vars.*

class Array2DSize(
        val array: Evaluation<Array2DVar<*>>
) : Evaluation<IntVar>(VarType.INT) {

    override fun onExecute(scope: Scope, executor: Executor, output: MutableOutput?) =
            IntVar[array.execute(scope, executor, output).value.size]

}