package com.troystopera.jkode.vars

import com.troystopera.jkode.exceptions.runtime.ArrayIndexException

open class Array2DVar<T : JVar<*>>(
        val arrayVarType: VarType<T>,
        array: Array<Array<T?>>?
) : JVar<Array<Array<T?>>>(array) {

    override fun getVarType(): VarType<Array2DVar<T>> = VarType.ARRAY2D[arrayVarType]

    override fun asEval(): JVarEvaluation<Array2DVar<T>> = JVarEvaluation(this, getVarType())

    /* operator fun get(rowIndex: Int, colIndex: Int): T = when {
        index >= value.size -> throw ArrayIndexException(value.size, index)
        else -> value[rowIndex][colIndex] ?: arrayVarType.NULL
    } */

    operator fun get(rowIndex: Int, colIndex: Int): T = value[rowIndex][colIndex] ?: arrayVarType.NULL

    /* fun set(rowIndex: Int, colIndex: Int, value: T?) = when {
         index >= this.value.size -> throw ArrayIndexException(this.value.size, index)
        else ->
        this.value[rowIndex][colIndex] = value
    } */
    fun set(rowIndex: Int, colIndex: Int, value: T?) {
        this.value[rowIndex][colIndex] = value
    }

    companion object {
        val NULL = Array2DVar<JVar<*>>(VarType.UNIT, null)
    }

}