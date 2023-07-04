const getLocalData = (name) => {
    return JSON.parse(localStorage.getItem(name));
}

const clearLocalData = (name) => {
    localStorage.removeItem(name)
}

const setLocalData = (name, data) => {
    localStorage.setItem(name, JSON.stringify(data))
}

export { getLocalData, clearLocalData, setLocalData }