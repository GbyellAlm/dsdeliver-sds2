import './styles.css'
import {ReactComponent as YouTube} from './youtube.svg'
import {ReactComponent as Linkedin} from './linkedin.svg'
import {ReactComponent as Instagram} from './instagram.svg'

function Footer() {
    return (
        <footer className="main-footer">
            App desenvolvido durante a 2ª ed. do evento Semana DevSuperior
            <div className="footer-icons">
                <a href="https://youtube.com/devsuperior" target="_new">
                    <YouTube />
                </a>
                <a href="https://www.linkedin.com/school/devsuperior/" target="_new">
                    <Linkedin />
                </a>
                <a href="https://instagram.com/devsuperior.ig" target="_new">
                    <Instagram />
                </a>
            </div>
        </footer>
    )
}

export default Footer;