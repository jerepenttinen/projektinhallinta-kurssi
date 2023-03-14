
const ProfileContacts = () => {
    return(
        <div className="d-flex align-items-center">
            <div className="d-inline-block h-25">
                <img src={"https://secure.gravatar.com/avatar/5586197d3539ebe07272af21926b496f?s=1920&d=mm&r=g"}
                    className="rounded-circle me-4"
                    style={{height:100}} >
                </img>
            </div>
            <div className="d-inline-block">
                <h3>Etunimi Sukunimi</h3>
                <p>Kuvaus</p>
            </div>
        </div>
    )
}


export default ProfileContacts;