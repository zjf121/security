package org.example.vo

class ResultVo<T>
    ( var data: Any? = null,
      var msg: String? = null,
      var code: String? = null)

    fun <T> ok(data: T? = null, msq: String? = null): ResultVo<T> = ResultVo(data, msq, "OK")

    fun <T> T?.success(msq: String? = null): ResultVo<T> = ResultVo(this, msq, "OK")

    fun <T> T?.fail(msq: String? = null): ResultVo<T> = ResultVo(this, msq, "fail")

