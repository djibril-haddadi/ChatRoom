import React from 'react'
import './App.css'

class UVsList extends React.Component {
    render() {
        return (
            <div className="uvs-list">
                <h1>Liste des UVs pour {this.props.name}</h1>
                <ul>
                    <li>SR03</li>
                    <li>AI16</li>
                    <li>SR02</li>
                </ul>
            </div>
        )
    }
}

export default UVsList // Export par défaut obligatoire
