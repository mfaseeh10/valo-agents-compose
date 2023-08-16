package mfaseeh.compose.valorantagents.common.exception

class CustomException(val errorMessage: String) : Exception() {
    override val message: String = errorMessage
}
