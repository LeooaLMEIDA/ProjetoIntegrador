const validateRequired = (value, required, object, messageRequired, minLength, messageLength) => {
  if (!value && required) {
    setError(object, messageRequired)
    return false
  }

  else if (value && typeof (value) == 'number') {
    setValid(object)
    return true
  }

  else if (value && (value.trim().length < minLength)) {
    setError(object, messageLength)
    return false
  }

  else {
    setValid(object)
    return true
  }
}

const validateDate = (value, required, object, messageRequired) => {
  if (!value && required) {
    setError(object, messageRequired)
    return false
  }

  else {
    setValid(object)
    return true
  }
}



const validateFile = (value, required, object, messageRequired) => {

  if (!value && required) {
    setError(object, messageRequired)
    return false
  }

  else {
    setValid(object)
    return true
  }
}

const validateEmail = (value, object, message) => {
  let reg = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/

  !reg.test(value)
    ? setError(object, message)
    : setValid(object)
}

const setError = (object, message) => {
  object.error = message
  object.hasError = true
}

const setValid = (object) => {
  object.error = ''
  object.hasError = false
}

module.exports = {
  validateRequired,
  validateEmail,
  validateFile,
  validateDate,
  setError,
  setValid
}