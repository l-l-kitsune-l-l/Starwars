const types = ['planets', 'films', 'people', 'starships', 'vehicles', 'species'];

window.onload = () => {
    types.forEach(async type => {
        const res = await fetch(`https://swapi.tech/api/${type}`);
        const data = await res.json();
        const select = document.getElementById(type);
        select.innerHTML = '';
        const items = data.results || data.result || [];
        items.forEach(item => {
            const option = document.createElement('option');
            option.value = item.uid || item.id;
            option.textContent = item.name || item.title || 'Sans nom';
            select.appendChild(option);
        });
    });
};

async function fetchDetails(type) {
    const uid = document.getElementById(type).value;
    const res = await fetch(`https://swapi.tech/api/${type}/${uid}`);
    const data = await res.json();
    const props = data.result.properties;

    let html = `<h3 style='color:#fff;'>${props.name || props.title}</h3>`;
    for (const key in props) {
        const value = props[key];

        if (Array.isArray(value) && value.length > 0 && typeof value[0] === 'string' && value[0].startsWith('https://')) {
            html += `<p><span class='label'>${key.replace(/_/g, ' ')}:</span> <ul>`;
            for (const url of value) {
                try {
                    const subRes = await fetch(url);
                    const subData = await subRes.json();
                    const name = subData.result.properties.name || subData.result.properties.title;
                    html += `<li>${name}</li>`;
                } catch (err) {
                    html += `<li>Erreur de chargement</li>`;
                }
            }
            html += `</ul></p>`;
        } else {
            html += `<p><span class='label'>${key.replace(/_/g, ' ')}:</span> ${value}</p>`;
        }
    }
    document.getElementById(`details-${type}`).innerHTML = html;
}
