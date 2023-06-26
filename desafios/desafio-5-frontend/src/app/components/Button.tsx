"use client";

const Button: React.FC<any> = (prop) => {
    return (
        <button 
            onClick={prop.onClick} 
            className={`mx-1 px-3 py-2 text-white text-center tracking-tight ${prop.color} rounded-lg`}>
                {prop.title}
        </button>
    );
}

export default Button;