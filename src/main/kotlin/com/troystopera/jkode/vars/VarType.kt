package com.troystopera.jkode.vars

import com.troystopera.jkode.util.Caster

sealed class VarType<out T : JVar<*>>(
        val name: String,
        val NULL: T
) {

    object ARRAY : ArrayType<JVar<*>>(UNIT, "Array", ArrayVar.NULL) {
        operator fun <T : JVar<*>> get(type: VarType<T>) =
                object : ArrayType<T>(type, type.name + "Array", ArrayVar(type, null)) {}
    }

    object ARRAY2D: Array2DType<JVar<*>>(UNIT, "Array2D", Array2DVar.NULL) {
        operator fun <T : JVar<*>> get(type: VarType<T>) =
                object : Array2DType<T>(type, type.name + "Array2D", Array2DVar(type, null)) {}
    }

    object UNIT : VarType<UnitVar>("Unit", UnitVar)

    object BOOLEAN : VarType<BooleanVar>("Boolean", BooleanVar.NULL)

    object INT : VarType<IntVar>("Int", IntVar.NULL)

    object STRING : VarType<StringVar>("String", StringVar.NULL)

    override fun equals(other: Any?) =
            if (other is VarType<*>) name == other.name
            else false

    override fun hashCode() = name.hashCode()

    fun castOrNull(value: Any): T? = Caster.safeCast(NULL, value)

}

abstract class ArrayType<T : JVar<*>>(var elementType: VarType<*>, name: String, NULL: ArrayVar<T>) :
        VarType<ArrayVar<T>>(name, NULL)

abstract class Array2DType<T : JVar<*>>(var elementType: VarType<*>, name: String, NULL: Array2DVar<T>):
        VarType<Array2DVar<T>>(name, NULL)
