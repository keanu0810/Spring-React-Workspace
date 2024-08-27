import React, { useState } from 'react';
import axios from 'axios';

function App() {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [files, setFiles] = useState([]);

    return (
        <div className="App">
            <form>
                <div>
                    <label>제목:</label>
                    <input/>
                </div>
                <div>
                    <label>내용:</label>
                    <textarea  />
                </div>
                <div>
                    <label htmlFor='a'>이미지선택:</label>
                    <input multiple type="file" style="display:none;" id="a" />
                </div>
                <button>Submit</button>
            </form>
        </div>
    );
}

export default App;
